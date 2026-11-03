package com.example.lab2.data.di

import com.example.lab2.data.TracksRepositoryImpl
import com.example.lab2.domain.api.TracksRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<TracksRepository> { TracksRepositoryImpl() }
}