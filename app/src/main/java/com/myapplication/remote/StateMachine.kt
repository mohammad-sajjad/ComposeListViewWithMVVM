package com.myapplication.remote

import kotlinx.coroutines.flow.MutableStateFlow

typealias StateMachine = MutableStateFlow<DataFetchState>

fun createStateMachine(): StateMachine = MutableStateFlow(DataFetchState.Loading)

