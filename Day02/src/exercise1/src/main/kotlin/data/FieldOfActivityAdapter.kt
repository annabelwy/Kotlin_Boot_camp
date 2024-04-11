package data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class FieldOfActivityAdapter {
    @FromJson
    fun fromJson(json: String): FieldOfActivity {
        return when (json) {
            "IT" -> FieldOfActivity.IT
            "banking" -> FieldOfActivity.BANKING
            "public services" -> FieldOfActivity.PUBLIC_SERVICES
            else -> throw IllegalArgumentException("Unknown field of activity: $json")
        }
    }

    @ToJson
    fun toJson(field: FieldOfActivity): String {
        return field.trueName
    }
}
enum class FieldOfActivity(val trueName:String){
    IT ("IT"),
    BANKING ("Banking"),
    PUBLIC_SERVICES("Public services")
}