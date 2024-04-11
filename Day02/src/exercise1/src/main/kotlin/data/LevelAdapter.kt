package data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class LevelAdapter {
    @FromJson
    fun fromJson(json: String): Level {
        return when (json) {
            "junior" -> Level.JUNIOR
            "middle" -> Level.MIDDLE
            "senior" -> Level.SENIOR
            else -> throw IllegalArgumentException("Unknown level: $json")
        }
    }

    @ToJson
    fun toJson(level: Level): String {
        return level.trueName
    }
}
enum class Level(val trueName: String){
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior")
}