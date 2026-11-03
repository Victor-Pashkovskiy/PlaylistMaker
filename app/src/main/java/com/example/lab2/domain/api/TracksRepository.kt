package com.example.lab2.domain.api
import com.example.lab2.domain.model.Track

interface TracksRepository {
    suspend fun searchTracksContains(expression: String): List<Track>
}