package com.sunggil.cleanarchitecture.domain

import android.os.Looper
import android.util.Log
import java.util.*

class Util {
    companion object {
        private val appCheckerKeyLength = 16

        fun getEncodeTime(token : String) : String {
            try {
                val key = token.substring(token.length - this.appCheckerKeyLength, token.length)
                val timeEncodeUtil = AES128Util(key)
                val timeInMillisecond = Calendar.getInstance().timeInMillis.toString()
                val encodeTime = timeEncodeUtil.encode(timeInMillisecond)
                Log.e("SG2", "getEncodeTime() key: $key || encodeTime : $encodeTime")
                return encodeTime
            } catch (e : Exception) {
                return ""
            }
        }

        fun checkMainLooper() = Looper.myLooper() == Looper.getMainLooper()

        fun String.toYNBoolean() : Boolean = this == "Y"

        fun Boolean.toYNString() : String = this.toString()
    }

}