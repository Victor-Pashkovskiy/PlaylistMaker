package com.example.lab2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit
) {

    val blueColor = Color(0xFF3772E7)
    val textColor = Color.Black
    val iconColor = Color(0xFFAEAFB4)
    val backgroundColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // --- СИНЯЯ ШАПКА ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(blueColor)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Playlist Maker",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- СПИСОК КНОПОК ---

        MenuItem(
            text = "Поиск",
            icon = Icons.Default.Search,
            iconColor = iconColor,
            textColor = textColor,
            onClick = onSearchClick
        )


        MenuItem(
            text = "Плейлисты",
            icon = Icons.Default.List,
            iconColor = iconColor,
            textColor = textColor,
            onClick = {  }
        )

        // Кнопка Избранное
        MenuItem(
            text = "Избранное",
            icon = Icons.Default.FavoriteBorder,
            iconColor = iconColor,
            textColor = textColor,
            onClick = { }
        )

        // Кнопка Настройки
        MenuItem(
            text = "Настройки",
            icon = Icons.Default.Settings,
            iconColor = iconColor,
            textColor = textColor,
            onClick = onSettingsClick
        )
    }
}

@Composable
fun MenuItem(
    text: String,
    icon: ImageVector,
    iconColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            color = textColor,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Перейти",
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
    }
}