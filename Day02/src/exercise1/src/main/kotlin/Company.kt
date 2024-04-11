package main.kotlin
import java.io.File
import kotlin.collections.List
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.*
import data.*

data class Company(
    @Json(name = "name")
    val name:String,
    @Json(name = "field_of_activity")
    val field: FieldOfActivity, // enum
    @Json(name = "vacancies")
    var vac: List<Vacancies>,
    val contacts: String)

@JsonClass(generateAdapter = true)
data class CompanyListWrapper(
    val listOfCompanies: List<Company> // так как перед компаниями есть пункт listOfCompanies
)

fun readCompaniesFromJsonFile(jsonFilePath: String): List<Company> {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(FieldOfActivityAdapter())
        .add(ProfessionAdapter())
        .add(LevelAdapter())
        .build()
    val adapter = moshi.adapter(CompanyListWrapper::class.java)

    val json = File(jsonFilePath).readText()
    val wrapper = adapter.fromJson(json)
    return wrapper?.listOfCompanies ?: emptyList()
}

data class Vacancies (
    @Json(name = "profession")
    var profession: Profession, // enum
    @Json(name = "level")
    val level: Level, // enum
    @Json(name = "salary")
    val salary: Int
)
