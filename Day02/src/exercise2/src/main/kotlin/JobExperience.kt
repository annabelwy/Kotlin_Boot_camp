package org.example

import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

data class JobExperience (
    @Json(name = "date_start")
    val dateStart: Date,
    @Json(name = "date_end")
    val dateEnd: Date,
    @Json(name = "company_name")
    val companyName: String,
    val description: String
){
    private fun dateFormatter(date: Date): String {
        val dateFormat = SimpleDateFormat("MM.yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }
    override fun toString(): String {
        return "JobExperience(dateStart='${dateFormatter(dateStart)}', dateEnd='${dateFormatter(dateEnd)}', companyName='$companyName}', description='$description')"
    }
}