
package com.example.eptracker.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "تنظیمات",
            style = MaterialTheme.typography.headlineMedium
        )

        HorizontalDivider()

        Text(
            text = "ظاهر برنامه",
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = if (isDarkTheme) "حالت تاریک" else "حالت روشن",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "تغییر پوسته برنامه",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Switch(
                checked = isDarkTheme,
                onCheckedChange = onThemeChange
            )
        }

        HorizontalDivider()

        Text(
            text = "درباره EpTracker",
            style = MaterialTheme.typography.titleMedium
        )

        Text("نسخه ۱٫۰٫۰")

        Text(
            text = "اپلیکیشن شخصی برای مدیریت و پیگیری سریال‌ها، انیمه‌ها، فیلم‌ها و کتاب‌ها."
        )

        HorizontalDivider()

        Text(
            text = "ذخیره‌سازی اطلاعات",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "اطلاعات این نسخه به‌صورت محلی روی دستگاه ذخیره می‌شوند و برای استفاده از قابلیت‌های اصلی به اینترنت نیاز نیست."
        )

        HorizontalDivider()

        Text(
            text = "حریم خصوصی",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "در نسخه فعلی، همگام‌سازی آنلاین و اتصال حساب کاربری فعال نیست."
        )
    }
}