package com.example.catapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catapp.presentation.viewmodel.PictureViewModel

@Composable
fun PictureListScreen(controller: NavHostController) {
    val viewModel = viewModel<PictureViewModel>()
    val pictures = viewModel.pictures

    LazyColumn (Modifier.fillMaxSize()
        .padding(top = 60.dp)) {
        items(pictures) { picture ->
            ListItem(
                modifier = Modifier.clickable { controller.navigate("element/${picture.objectID}") }
                    .padding(start = 16.dp),
                headlineContent = {
                    Text(text = picture.title, fontSize = 22.sp)
                }
            )
        }
    }
}