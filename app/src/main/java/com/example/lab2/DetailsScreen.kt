package com.example.lab2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.lab2.domain.model.Track

@Composable
fun DetailsScreen(track: Track) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Детали трека", fontSize = 24.sp)
        Text(text = track.trackName, fontSize = 18.sp)
        Text(text = track.artistName, fontSize = 16.sp)
    }
}