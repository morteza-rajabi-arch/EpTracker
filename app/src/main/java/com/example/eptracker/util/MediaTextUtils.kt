
package com.example.eptracker.util

fun mediaTypeToPersian(type: String): String {
    return when (type) {
        "SERIES" -> "سریال"
        "K_DRAMA" -> "کی‌دراما"
        "ANIME" -> "انیمه"
        "MOVIE" -> "فیلم"
        "BOOK" -> "کتاب"
        else -> "سایر"
    }
}

fun mediaStatusToPersian(status: String): String {
    return when (status) {
        "PLANNED" -> "برای بعد"
        "IN_PROGRESS" -> "در حال پیگیری"
        "COMPLETED" -> "تکمیل‌شده"
        "PAUSED" -> "متوقف‌شده"
        else -> "نامشخص"
    }
}

fun mediaProgressToPersian(
    type: String,
    currentSeason: Int,
    lastWatchedEpisode: Int,
    currentPage: Int,
    isMovieWatched: Boolean
): String {
    return when (type) {
        "SERIES", "K_DRAMA", "ANIME" ->
            "فصل $currentSeason، قسمت $lastWatchedEpisode"

        "BOOK" ->
            "صفحه $currentPage"

        "MOVIE" ->
            if (isMovieWatched) "تماشا شده" else "تماشا نشده"

        else -> ""
    }
}