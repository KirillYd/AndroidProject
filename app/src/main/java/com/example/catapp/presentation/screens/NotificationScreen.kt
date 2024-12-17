package com.example.catapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationScreen() {
    Column(modifier = Modifier.padding(top = 60.dp)) {
        Text(text = "Notification", style = MaterialTheme.typography.titleLarge)
    }
}