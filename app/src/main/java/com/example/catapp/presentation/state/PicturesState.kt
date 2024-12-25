package com.example.catapp.presentation.state

import androidx.compose.runtime.Stable
import com.example.catapp.model.PictureResponse
import com.example.catapp.model.ObjectDetails
import com.example.catapp.model.Picture

data class PicturesState(
    val picturesList: PictureResponse?= null,
)

data class DetailsState(
    val pictureDetails: ObjectDetails?= null
)