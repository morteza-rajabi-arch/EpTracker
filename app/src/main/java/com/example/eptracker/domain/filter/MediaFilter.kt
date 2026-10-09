
package com.example.eptracker.domain.filter

data class MediaFilter(
    val type: String? = null,
    val status: String? = null,
    val folderId: Long? = null,
    val query: String = ""
) {
    fun isEmpty(): Boolean {
        return type == null &&
                status == null &&
                folderId == null &&
                query.isBlank()
    }
}