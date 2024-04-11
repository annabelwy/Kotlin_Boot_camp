import data.Incident
import main.kotlin.Tetragon
import main.kotlin.Triangular

fun main(args: Array<String>) {
    println("Enter zone parameters:")
    val expression = readln()
    validator(expression, 1)
    val shape = getShape(expression)
    shape.printInformation()
    println("Enter an incident coordinates:")
    val zoneCoord = readln ()
    validator (zoneCoord,2)
    val byZoneDigits = exprDivision(zoneCoord)
    val incident = Incident (byZoneDigits[0],byZoneDigits[1])
    incident.printInfo()
    shape.pritnRes(incident)
}

fun exprDivision(expression: String): List<Int> {
    val byPairs = expression.split(" ")
    return (byPairs.joinToString(separator = ";")).split(";")
        .map { it.toInt() } // выкинет ошибку, если будет не Int число
}

// если кейс 1 то валидатор для зоны, иначе для инцидента
fun validator(expression: String, case: Int) {
    val byDigits = exprDivision(expression)
    val numOfDelimiter = expression.split(";").lastIndex
    when (case) {
        1 -> when {
            ((byDigits.size != 3 && numOfDelimiter != 1 && byDigits.size != numOfDelimiter * 2)) -> throw Exception("Wrong data")
            (byDigits.size == 3 && numOfDelimiter == 1 && byDigits[2] <= 0) -> throw Exception("Wrong radius")
        }

        2 -> when {
            ((byDigits.size != 2 || numOfDelimiter != 1)) -> throw Exception("Wrong incident coordinates")
        }
    }
}

fun getShape(expression: String): Zone {
    val byDigits = exprDivision(expression)

    return when (byDigits.size) {
        3 -> {
            Circle(Pair(byDigits[0], byDigits[1]), byDigits[2])
        }

        6 -> {
            Triangular(Pair(byDigits[0], byDigits[1]), Pair(byDigits[2], byDigits[3]), Pair(byDigits[4], byDigits[5]))
        }

        8 -> {
            Tetragon(
                Pair(byDigits[0], byDigits[1]), Pair(byDigits[2], byDigits[3]), Pair(byDigits[4], byDigits[5]),
                Pair(byDigits[6], byDigits[7])
            )
        }

        else -> throw IllegalArgumentException("Unsupported number of digits in expression")
    }
}

