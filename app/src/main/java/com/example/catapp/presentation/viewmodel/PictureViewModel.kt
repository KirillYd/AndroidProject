package com.example.catapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.model.ObjectDetails
import com.example.catapp.model.PictureResponse
import com.example.catapp.model.Picture

import com.example.catapp.presentation.state.PicturesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.catapp.network.Instance
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@HiltViewModel
class PictureViewModel @Inject constructor() :
    BaseViewModel<PicturesState>(initialState = PicturesState())
{

    private var repository: Instance

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        repository = Instance(client)
    }

    private val _pictures = MutableStateFlow<PictureResponse>(PictureResponse(total = 0, objectIDs = emptyList()))
    val pictures: StateFlow<PictureResponse> = _pictures.asStateFlow()

    private val _pictureTitles = MutableStateFlow<List<String>>(emptyList())
    val pictureTitles: StateFlow<List<String>> = _pictureTitles.asStateFlow()

    private val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded.asStateFlow()


    fun fetchRandomPictures() {
        if (_isDataLoaded.value) return

        viewModelScope.launch {
            val response = repository.api.getObjects()
            val randomPictures = response.objectIDs?.shuffled()?.take(8) ?: emptyList()
            _pictures.value = PictureResponse(total = response.total, objectIDs = randomPictures)
            fetchPictureDetails(randomPictures)
            _isDataLoaded.value = true
        }
    }

    fun fetchPictureDetails(objectIds: List<Int>) {
        viewModelScope.launch {
            val titles = mutableListOf<String>()
            objectIds.forEach { id ->
                val picture = repository.api.getObjectDetails(id)
                titles.add(picture.title)
            }
            _pictureTitles.value = titles
        }
    }
}
