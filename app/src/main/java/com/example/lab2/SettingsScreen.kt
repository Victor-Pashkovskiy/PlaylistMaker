package com.example.lab2

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    onBackClick: () -> Boolean
) {
    val context = LocalContext.current

    val shareText = stringResource(id = R.string.share_text)
    val supportEmail = stringResource(id = R.string.support_email)
    val emailSubject = stringResource(id = R.string.email_subject)
    val emailBody = stringResource(id = R.string.email_body)
    val offerUrl = stringResource(id = R.string.offer_url)

    val iconColor = Color(0xFFAEAFB4)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Назад",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = R.string.settings_title),
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }




        SettingsItem(
            text = stringResource(id = R.string.share_app),
            iconColor = iconColor,
            onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareText)
                }
                context.startActivity(Intent.createChooser(shareIntent, "Поделиться"))
            }
        )


        SettingsItem(
            text = stringResource(id = R.string.write_to_support),
            iconColor = iconColor,
            onClick = {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(supportEmail))
                    putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                    putExtra(Intent.EXTRA_TEXT, emailBody)
                }
                context.startActivity(emailIntent)
            }
        )

        // Пользовательское соглашение
        SettingsItem(
            text = stringResource(id = R.string.user_agreement),
            iconColor = iconColor,
            onClick = {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(offerUrl))
                context.startActivity(browserIntent)
            }
        )
    }
}

@Composable
fun SettingsItem(
    text: String,
    iconColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = iconColor
        )
    }
}