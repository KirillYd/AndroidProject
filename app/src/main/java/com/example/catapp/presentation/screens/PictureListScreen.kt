package com.example.catapp.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catapp.presentation.viewmodel.PictureViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.catapp.model.ObjectDetails
import com.example.catapp.model.PictureResponse

@Composable
fun PictureListScreen(onDetailsScreen: (id: Int) -> Unit) {
    val picturesViewModel = hiltViewModel<PictureViewModel>()
    val picturesState = picturesViewModel.pictures.collectAsState()
    val pictureTitlesState = picturesViewModel.pictureTitles.collectAsState()

    // Загружаем случайные картины при первом рендере
    val hasFetchedPictures = remember { mutableStateOf(false) }

    if (!hasFetchedPictures.value) {
        LaunchedEffect(Unit) {
            picturesViewModel.fetchRandomPictures()
            hasFetchedPictures.value = true
        }
    }

    // Экран с прокручиваемым списком
    PictureList(
        picturesState.value.objectIDs,
        pictureTitlesState.value,
        onDetailsScreen
    )
}


@SuppressLint("SwitchIntDef")
@Composable
fun PictureList(
    ObjectIDs: List<Int>,
    pictureTitles: List<String>,
    onDetailsScreen: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 60.dp)
    ) {
        itemsIndexed(ObjectIDs) { index, objectID ->
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailsScreen(objectID) }
                    .padding(start = 20.dp),
                headlineContent = {

                    val title = pictureTitles.getOrNull(index) ?: "Загружается..."
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }
}
