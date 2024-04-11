package org.example
import java.text.SimpleDateFormat
import java.util.Locale
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import java.util.Date
data class CandidateInfo(
    val name:String,
    val profession: String,
    val sex: String,
    @Json(name = "birth_date")
    val birthDate: Date,
    val contacts: Contacts
){
    private fun dateFormatter(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.format(birthDate)
    }
    override fun toString(): String {
        return "CandidateInfo(name='$name', profession='$profession', sex='$sex', birthDate='${dateFormatter()}', contacts=$contacts)"
    }
}
@JsonClass(generateAdapter = true)
data class CandidateInfoWarpper(
    @Json(name = "candidate_info")
    val candInfo: CandidateInfo, // так как перед компаниями есть пункт listOfCompanies
    val education:List<Education>,
    @Json(name = "job_experience")
    val jobExperience: List<JobExperience>,
    @Json(name = "free_form")
    val freeForm: String
)

data class Contacts (
    val phone: String,
    val email: String
)

fun readCandidateInfoFromJsonFile(jsonFilePath: String): CandidateInfoWarpper {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(DateAdapter())
        .build()
    val adapter = moshi.adapter(CandidateInfoWarpper::class.java)

    val json = File(jsonFilePath).readText()  // взять текст
    return adapter?.fromJson(json) ?: throw IllegalStateException("Failed to parse JSON")
}

class DateAdapter {
    private val dateFormatDayMonthYear = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    private val dateFormatMonthYear = SimpleDateFormat("MM.yyyy", Locale.getDefault())
    private val dateFormatYear = SimpleDateFormat("yyyy", Locale.getDefault())

    @FromJson
    fun fromJson(dateString: String): Date {
        return try {
            dateFormatDayMonthYear.parse(dateString)
        } catch (e: Exception) {
            try {
                dateFormatMonthYear.parse(dateString)
            } catch (e: Exception) {
                try {
                    dateFormatYear.parse(dateString)
                } catch (e: Exception) {
                    Date()
                }
            }
        } ?: Date()
    }
    @ToJson
    fun toJson(date: Date): String {
        return date.toString()
    }
}