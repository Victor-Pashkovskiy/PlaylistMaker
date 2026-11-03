package com.example.lab2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab2.domain.model.Track
import com.example.lab2.view_model.SearchState
import com.example.lab2.view_model.SearchViewModel

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navigateToDetailScreen: (Track) -> Unit
) {
    val screenState by searchViewModel.searchScreenState.collectAsState()
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Поиск", fontSize = 16.sp, color = Color.Gray) },
            leadingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            searchViewModel.searchTracks(text)
                            focusManager.clearFocus()
                        }
                    },
                    imageVector = Icons.Filled.Search,
                    tint = Color.Gray,
                    contentDescription = "Поиск"
                )
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable {
                            text = ""
                            searchViewModel.clearSearch()
                        },
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Очистить"
                    )
                }
            },
            singleLine = true
        )

        SearchResults(
            screenState = screenState,
            modifier = Modifier.padding(top = 16.dp),
            onTrackClick = navigateToDetailScreen
        )
    }
}

@Composable
private fun SearchResults(
    screenState: SearchState,
    modifier: Modifier,
    onTrackClick: (Track) -> Unit
) {
    when (screenState) {
        is SearchState.Initial -> {}
        is SearchState.Searching -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is SearchState.Success -> {
            LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(screenState.foundList) { track ->
                    TrackListItem(track = track, onClick = { onTrackClick(track) })
                }
            }
        }
        is SearchState.Fail -> {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = screenState.error, fontSize = 18.sp, color = Color.Black)
            }
        }
    }
}

@Composable
private fun TrackListItem(track: Track, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = null,
            modifier = Modifier.size(45.dp),
            tint = Color.LightGray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(track.trackName, fontSize = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(track.artistName, fontSize = 11.sp, color = Color.Gray, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text("•", fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(horizontal = 4.dp))
                Text(track.trackTime, fontSize = 11.sp, color = Color.Gray, maxLines = 1)
            }
        }
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}