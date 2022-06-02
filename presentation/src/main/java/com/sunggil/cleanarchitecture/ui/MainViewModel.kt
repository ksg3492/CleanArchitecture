package com.sunggil.cleanarchitecture.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunggil.cleanarchitecture.base.BaseNetworkViewModel
import com.sunggil.cleanarchitecture.domain.model.Category
import com.sunggil.cleanarchitecture.domain.model.Version
import com.sunggil.cleanarchitecture.domain.usecase.GetCategoryUseCase
import com.sunggil.cleanarchitecture.domain.usecase.GetVersionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val GetVersionUseCase : GetVersionUseCase,
    private val GetCategoryUseCase : GetCategoryUseCase
) : BaseNetworkViewModel() {
    private val API_NAME_GET_VERSION = "API_NAME_GET_VERSION"
    private val API_NAME_GET_CATEGORY = "API_NAME_GET_CATEGORY"

    private var versionPublishSubject : PublishSubject<Boolean> = PublishSubject.create()
    private var categoryPublishSubject : PublishSubject<Boolean> = PublishSubject.create()

    private val disposal = CompositeDisposable()

    private var _splashFinished : MutableLiveData<Boolean?> = MutableLiveData(null)
    val splashFinished : LiveData<Boolean?> = _splashFinished

    private var _categoryList : MutableLiveData<ArrayList<Category>> = MutableLiveData(arrayListOf())
    val categoryList : LiveData<ArrayList<Category>> = _categoryList

    private var _version : MutableLiveData<Version?> = MutableLiveData(null)
    val version : LiveData<Version?> = _version

    private fun setSplashFinished(finished : Boolean) {
        _splashFinished.value = finished
    }

    private fun setCategoryList(list : ArrayList<Category>) {
        _categoryList.value = list
    }

    private fun setVersion(version : Version?) {
        _version.value = version
    }

    fun loadSplash() {
        /**
         * 2개의 api 성공해야 콜백
         */
        val combineObserver = Observable.combineLatest(
            versionPublishSubject,
            categoryPublishSubject,
            BiFunction { t1, t2 -> t1 && t2 }
        )
        this.versionPublishSubject.subscribe(getSubscribeObserver())
        this.categoryPublishSubject.subscribe(getSubscribeObserver())

        combineObserver
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.setSplashFinished(it)
            }

        this.getVersion()
        this.getCategory()
    }

    /**
     * 단순 dispose 를 위한 옵저버
     */
    private fun getSubscribeObserver() : Observer<Boolean> {
        return object : Observer<Boolean> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
                disposal.add(d)
            }

            override fun onNext(t: Boolean?) {
            }

            override fun onError(e: Throwable?) {
            }
        }
    }


    private fun getVersion() {
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
                            setVersion(it)
                            versionPublishSubject.onNext(true)
                            return
                        }
                        versionPublishSubject.onNext(false)
                    }

                    override fun onError(e : Throwable?) {
                        Log.e("SG2", "getVersion error : ", e)
                    }
                })
        )
    }

    private fun getCategory() {
        cancelObserver(API_NAME_GET_CATEGORY)
        addObserver(API_NAME_GET_CATEGORY,
            this.GetCategoryUseCase.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { setLoading(true) }
                .doAfterTerminate { setLoading(false) }
                .subscribeWith(object : DisposableSingleObserver<ArrayList<Category>>() {
                    override fun onSuccess(t : ArrayList<Category>?) {
                        t?.let {
                            Log.e("SG2", "getCategory category : $it")
                            setCategoryList(it)
                            categoryPublishSubject.onNext(true)
                            return
                        }
                        categoryPublishSubject.onNext(false)
                    }

                    override fun onError(e : Throwable?) {
                        Log.e("SG2", "getCategory error : ", e)
                    }
                })
        )
    }

    override fun onCleared() {
        this.disposal.clear()
        super.onCleared()
    }
}