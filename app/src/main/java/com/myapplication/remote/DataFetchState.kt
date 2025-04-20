package com.myapplication.remote

import com.satta.d_matka.error.StandardError

sealed class DataFetchState {
    data object Loading: DataFetchState()
    class Success(val message: String? = null): DataFetchState()
    class Error(val error: StandardError): DataFetchState()
}