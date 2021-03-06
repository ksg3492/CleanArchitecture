package com.sunggil.cleanarchitecture.data.di

import android.util.Log
import com.sunggil.cleanarchitecture.data.BuildConfig
import com.sunggil.cleanarchitecture.data.Name
import com.sunggil.cleanarchitecture.domain.ConstValue
import com.sunggil.cleanarchitecture.domain.ServiceValue
import com.sunggil.cleanarchitecture.domain.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltDataLayer {
    @Singleton
    @Provides
    fun providesOkHttpClient() : OkHttpClient {
        Log.e("SG2","providesOkHttpClient()")
        return OkHttpClient.Builder()
            .addInterceptor(getInterceptor())
            .addInterceptor(HeaderInterceptor())
            .connectTimeout(ConstValue.CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(ConstValue.CALL_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient : OkHttpClient) : Retrofit {
        Log.e("SG2","providesRetrofit()")
        return Retrofit.Builder()
            .baseUrl(Name.a())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}

/**
 * log용 interceptor
 */
fun getInterceptor() : HttpLoggingInterceptor {
    val intercepterLevel = if (ServiceValue.isDebug) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(intercepterLevel)
    return interceptor
}

/**
 * 헤더 생성
 */
fun getHeader(requestBuilder : Request.Builder) : Request.Builder {
    val time = Util.getEncodeTime(Name.b() ?: "")
    requestBuilder
        .header(ConstValue.HEADER_DEVICE_TYPE, "AOS")
        .header(ConstValue.HEADER_APP_VERSION, BuildConfig.VERSION_NAME)
        .header(ConstValue.HEADER_DEVICE_NAME, android.os.Build.MODEL)
        .header(ConstValue.HEADER_DEVICE_TOKEN, "Bearer ${Name.b()}")
        .header(ConstValue.HEADER_APP_CHECKER, time)
    return requestBuilder
}

/**
 * auth token header interceptor
 */
class HeaderInterceptor() : Interceptor {
    override fun intercept(chain : Interceptor.Chain) : Response {
        var newRequestWithToken = chain.request().newBuilder()
        newRequestWithToken = getHeader(newRequestWithToken)
        return chain.proceed(newRequestWithToken.build())
    }
}