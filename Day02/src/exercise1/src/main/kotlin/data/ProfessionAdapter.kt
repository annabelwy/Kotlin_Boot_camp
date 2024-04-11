package data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ProfessionAdapter {
    @FromJson
    fun fromJson(json: String): Profession {
        return when (json) {
            "developer" -> Profession.DEVELOPER
            "qa" -> Profession.QA
            "pm" -> Profession.PROJECT_MANAGER
            "analyst" -> Profession.ANALYST
            "designer" -> Profession.DESIGNER
            else -> throw IllegalArgumentException("Unknown profession: $json")
        }
    }

    @ToJson
    fun toJson(profession: Profession): String {
        return profession.trueName
    }
}

enum class Profession(val trueName: String){
    DEVELOPER("Developer"),
    QA("QA"),
    PROJECT_MANAGER("Project Manager"),
    ANALYST("Analyst"),
    DESIGNER("Designer")
}