package com.example.lab2

import android.app.Application
import com.example.lab2.data.di.repositoryModule
import com.example.lab2.view_model.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@MyApplication)
            modules(
                repositoryModule,
                module {
                    viewModel { SearchViewModel(get()) }
                }
            )
        }
    }
}