package com.sunggil.cleanarchitecture.data

object Name {
    init {
        System.loadLibrary("native-lib")
    }

    /**
     * vision baseUrl
     * @return
     */
    external fun a() : String?
}
