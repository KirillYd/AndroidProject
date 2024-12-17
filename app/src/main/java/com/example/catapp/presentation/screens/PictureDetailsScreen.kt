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
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter


@Composable
fun PictureDetailsScreen(controller: NavHostController, elementId: Int) {
    val viewModel = viewModel<PictureViewModel>()
    val picture = viewModel.getById(elementId);

    if(picture != null){
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp)

        ) {
            Image(
                painter = rememberAsyncImagePainter(picture.primaryImage),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
            )
            Text(text = "Picture name: ${picture.title}", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Accession Year: ${picture.accessionYear}", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Culture: ${picture.culture}", fontSize = 22.sp)
        }
    }
    else{
        Text("Picture not found")
    }
}