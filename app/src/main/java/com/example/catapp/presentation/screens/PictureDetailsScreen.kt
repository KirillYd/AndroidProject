package com.example.catapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.catapp.presentation.viewmodel.PictureViewModel
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.catapp.model.ObjectDetails
import com.example.catapp.presentation.viewmodel.DetailsViewModel


@Composable
fun PictureDetailsScreen(controller: NavHostController, elementId: Int) {
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val pictureDetails = detailsViewModel.pictureDetails.collectAsState()

    LaunchedEffect(elementId) {
        detailsViewModel.fetchPictureDetails(elementId)
    }

    PictureDetails(pictureDetails.value)
}

@Composable
fun PictureDetails(
    pictureDetails: ObjectDetails?
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp)
    ) {
        if (pictureDetails?.primaryImage != "") {
            Image(
                painter = rememberAsyncImagePainter(pictureDetails?.primaryImage),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .padding(bottom = 10.dp)
                    .align(Alignment.CenterHorizontally)

            )
        }
        else{
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }
        Text(text = "Picture name: ${pictureDetails?.title}", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Accession Year: ${pictureDetails?.accessionYear}", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Culture: ${pictureDetails?.culture}", fontSize = 22.sp)

        if (pictureDetails == null) {
            CircularProgressIndicator(modifier = Modifier.wrapContentSize(Alignment.Center))
        }
    }
}