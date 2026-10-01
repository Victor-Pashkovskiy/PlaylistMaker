package com.example.lab2

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val searchItem = findViewById<LinearLayout>(R.id.searchItem)
        val playlistsItem = findViewById<LinearLayout>(R.id.playlistsItem)
        val favoritesItem = findViewById<LinearLayout>(R.id.favoritesItem)
        val settingsItem = findViewById<LinearLayout>(R.id.settingsItem)

        searchItem.setOnClickListener {
            Toast.makeText(this, "Поиск (заглушка)", Toast.LENGTH_SHORT).show()
        }
        playlistsItem.setOnClickListener {
            Toast.makeText(this, "Плейлисты (заглушка)", Toast.LENGTH_SHORT).show()
        }
        favoritesItem.setOnClickListener {
            Toast.makeText(this, "Избранное (заглушка)", Toast.LENGTH_SHORT).show()
        }
        settingsItem.setOnClickListener {
            Toast.makeText(this, "Настройки (заглушка)", Toast.LENGTH_SHORT).show()
        }
    }
}