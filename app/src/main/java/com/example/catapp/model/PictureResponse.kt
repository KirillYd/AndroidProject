package com.example.catapp.model

data class PictureResponse(
    val total: Int,
    val objectIDs: List<Int>
)

data class ObjectDetails(
    val objectID: Int,
    val accessionYear: String,
    val primaryImage: String,
    val title: String,
    val culture: String
)
