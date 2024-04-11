import data.Incident

abstract class Zone() {
    //    open z:Zone
    abstract val a: String
    abstract val phoneNum: String


    //    var numb =
    fun printInformation() {
        println("The zone info:")
        println("\tThe shape of zone: $a ")
        println("\tPhone number: $phoneNum\n")
    }

    abstract fun isZone(incident: Incident) : Boolean

    fun pritnRes(incident: Incident) {
        if (isZone(incident)) {
            println("An incident is in the zone")
        } else {
            println("An incident is not in the zone")
            println("Switch the applicant to the common number: 88008473824")
        }
    }
}


//val circle: Pair<Int, Int>, val sec: Pair<Int, Int> = Pair(0,0), val triangle:Pair<Int, Int> = Pair(0,0), var tetragon:Pair<Int, Int> = Pair(0,0), val r:Int= 0