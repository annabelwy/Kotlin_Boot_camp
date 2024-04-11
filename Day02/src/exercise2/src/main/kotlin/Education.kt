package org.example

import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

data class Education(
    val type: String,
    @Json(name = "year_start")
    val yearStart: Date, // поменять на год
    @Json(name = "year_end")
    val yearEnd: Date,
    val description: String,

){
    private fun dateFormatter(date: Date): String {
        val dateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }
    override fun toString(): String {
        return "Education(type='$type', yearStart='${dateFormatter(yearStart)}', yearEnd='${dateFormatter(yearEnd)}', description='$description')"
    }
}