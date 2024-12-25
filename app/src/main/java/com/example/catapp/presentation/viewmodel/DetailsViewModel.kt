package com.example.catapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.catapp.network.Instance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import com.example.catapp.presentation.state.DetailsState
import com.example.catapp.model.ObjectDetails
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor() :
    BaseViewModel<DetailsState>(initialState = DetailsState()) {
    private var repository: Instance

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        repository = Instance(client)
    }

    private val _pictureDetails = MutableStateFlow<ObjectDetails?>(null)
    val pictureDetails: StateFlow<ObjectDetails?> = _pictureDetails.asStateFlow()

    fun fetchPictureDetails(objectId: Int) {
        viewModelScope.launch {
            val picture = repository.api.getObjectDetails(objectId)
            _pictureDetails.value = picture
        }
    }
}