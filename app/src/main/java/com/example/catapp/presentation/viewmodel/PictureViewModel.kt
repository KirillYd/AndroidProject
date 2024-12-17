package com.example.catapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.catapp.data.PictureRepository
import com.example.catapp.model.Picture

class PictureViewModel(): ViewModel(){

    private val repository = PictureRepository()

    val pictures: List<Picture> = repository.getAll()

    fun getById(objectID: Int): Picture? {
        return repository.get(objectID)
    }
}