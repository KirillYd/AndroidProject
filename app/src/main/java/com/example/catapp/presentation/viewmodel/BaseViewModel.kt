package com.example.catapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State>(
    initialState: State,
) : ViewModel() {
    private val viewState = MutableStateFlow(initialState)

    fun getViewState(): StateFlow<State> = viewState
}