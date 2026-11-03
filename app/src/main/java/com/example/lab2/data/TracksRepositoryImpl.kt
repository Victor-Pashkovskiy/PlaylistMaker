package com.example.lab2.data

import com.example.lab2.domain.api.TracksRepository
import com.example.lab2.domain.model.Track
import kotlinx.coroutines.delay

class TracksRepositoryImpl : TracksRepository {
    private val tracksList = listOf(
        Track(1, "Yesterday (Remastered 2009)", "The Beatles", "02:05"),
        Track(2, "Here Comes The Sun", "The Beatles", "03:07"),
        Track(3, "No Reply", "The Beatles", "02:32"),
        Track(4, "Let It Be", "The Beatles", "04:03"),
        Track(5, "Girl", "The Beatles", "02:18")
    )

    override suspend fun searchTracksContains(expression: String): List<Track> {
        delay(3000)
        return tracksList.filter { track ->
            track.trackName.contains(expression, ignoreCase = true) ||
                    track.artistName.contains(expression, ignoreCase = true)
        }
    }
}