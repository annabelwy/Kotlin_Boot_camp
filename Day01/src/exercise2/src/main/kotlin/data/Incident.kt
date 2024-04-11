package data

import descriptions
import main.kotlin.mask
import phones
import kotlin.random.Random

data class Incident(
    val x: Int,
    val y: Int,
    val description: String = descriptions[Random.nextInt(descriptions.size)],
    val number: String? = phones[Random.nextInt(phones.size)],
    val type: IncidentType = when {
        (description.contains("cat")) -> IncidentType.CAT_ON_THE_TREE
        (description.contains("UFO")) ->IncidentType.UFO
        (description.contains("gas")) -> IncidentType.GAS_LEAK
        (description.contains("fire")) -> IncidentType.FIRE
                else -> IncidentType.OTHER
    }
) {
    fun printInfo() {
        println("The incident info:")
        println("\tDescription: $description")
        number?.let{
            println("\tPhone number: ${number.mask()}")
        }
        println("\tType: ${type.name.lowercase()}\n")
    }
}

enum class IncidentType {
    FIRE,
    CAT_ON_THE_TREE,
    UFO,
    GAS_LEAK,
    OTHER
    // ...
}

