import data.Incident
import main.kotlin.mask

abstract class Zone() {
    abstract val shape: String
    abstract val phoneNum: String
    private lateinit var commonNum: String

    fun printInformation() {
        println("The zone info:")
        println("\tThe shape of zone: $shape ")
        println("\tPhone number: ${phoneNum.mask()}\n")
    }

    abstract fun isZone(incident: Incident) : Boolean

    fun pritnRes(incident: Incident) {
        if (isZone(incident)) {
            println("An incident is in the zone")
        } else {
            commonNum = "88008473824"
            println("An incident is not in the zone")
            println("Switch the applicant to the common number: ${commonNum.mask()}")
        }
    }
}


//val circle: Pair<Int, Int>, val sec: Pair<Int, Int> = Pair(0,0), val triangle:Pair<Int, Int> = Pair(0,0), var tetragon:Pair<Int, Int> = Pair(0,0), val r:Int= 0