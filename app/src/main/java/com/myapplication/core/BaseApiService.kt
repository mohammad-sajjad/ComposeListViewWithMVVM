package com.myapplication.core

import com.myapplication.remote.ApiResponseCallBack
import com.satta.d_matka.error.StandardError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

open class BaseService {

    fun <T> makeRequest(call: Call<T>, responseCallback: ApiResponseCallBack<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        responseCallback.onSuccess(it)
                    } ?: run {
                        responseCallback.onError(
                            StandardError("Empty response body", "Unknown error")
                        )
                    }
                    return
                }

                when (response.code()) {
                    401 -> {
                        responseCallback.onError(
                            StandardError(
                                displayError = "Session expired. Please log in again.",
                                title = "Token Expired"
                            )
                        )
                    }

                    else -> {
                        responseCallback.onError(
                            StandardError(
                                displayError = response.message(),
                                title = "Unknown Error"
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()
                        val message = when (code) {
                            HttpsURLConnection.HTTP_UNAUTHORIZED -> "Session expired. Please log in again."
                            HttpsURLConnection.HTTP_FORBIDDEN -> "Forbidden error."
                            HttpsURLConnection.HTTP_INTERNAL_ERROR -> "Internal server error."
                            HttpsURLConnection.HTTP_BAD_REQUEST -> "Bad request."
                            else -> throwable.message().orEmpty()
                        }

                        responseCallback.onError(
                            StandardError(title = "Network Error", displayError = message)
                        )
                    }

                    else -> {
                        responseCallback.onError(
                            StandardError(
                                title = "Unknown Error",
                                displayError = throwable.message.orEmpty()
                            )
                        )
                    }
                }
            }
        })
    }
}
