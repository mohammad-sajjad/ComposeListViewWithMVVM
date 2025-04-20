package com.myapplication.remote

import com.satta.d_matka.error.StandardError

interface ApiResponseCallBack<T> {
    fun onSuccess(response: T)
    fun onError(error: StandardError)
}