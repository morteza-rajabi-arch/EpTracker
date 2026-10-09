
package com.example.eptracker.navigation

data class BottomNavItem(
    val title: String,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        title = "کتابخانه",
        route = Screen.Library.route
    ),
    BottomNavItem(
        title = "جست‌وجو",
        route = Screen.Search.route
    ),
    BottomNavItem(
        title = "پوشه‌ها",
        route = Screen.Folders.route
    ),
    BottomNavItem(
        title = "آمار",
        route = Screen.Stats.route
    ),
    BottomNavItem(
        title = "تنظیمات",
        route = Screen.Settings.route
    )
)