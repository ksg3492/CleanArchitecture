package com.sunggil.cleanarchitecture.data

object Name {
    init {
        System.loadLibrary("native-lib")
    }

    /**
     * API baseUrl
     * @return
     */
    external fun a() : String

    /**
     * API Token
     * @return
     */
    external fun b() : String

    /**
     * API Token decryptKey
     * @return
     */
    external fun c() : String
}
