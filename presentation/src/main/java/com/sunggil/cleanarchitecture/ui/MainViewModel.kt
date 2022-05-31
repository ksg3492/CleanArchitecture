package com.sunggil.cleanarchitecture.ui

import android.util.Log
import com.sunggil.cleanarchitecture.base.BaseNetworkViewModel
import com.sunggil.cleanarchitecture.domain.model.Version
import com.sunggil.cleanarchitecture.domain.usecase.GetVersionUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val GetVersionUseCase : GetVersionUseCase
) : BaseNetworkViewModel() {
    private val API_NAME_GET_VERSION = "API_NAME_GET_VERSION"

    fun getVersion() {
        cancelObserver(API_NAME_GET_VERSION)
        addObserver(API_NAME_GET_VERSION,
            this.GetVersionUseCase.getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { setLoading(true) }
                .doAfterTerminate { setLoading(false) }
                .subscribeWith(object : DisposableSingleObserver<Version>() {
                    override fun onSuccess(t : Version?) {
                        t?.let {
                            Log.e("SG2", "getVersion version : $it")
                        }
                    }

                    override fun onError(e : Throwable?) {
                        Log.e("SG2", "getVersion error : ", e)
                    }
                })
        )
    }
}