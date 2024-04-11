import data.Incident
import kotlin.math.pow
import kotlin.math.sqrt


class Circle(val center: Pair<Int, Int>, val r:Int) :Zone() {
    override val a = "circle"
//    val specificProperty1: Int = r
    override val phoneNum = "89275254876"
    override fun isZone(incident: Incident): Boolean {
        return sqrt(((center.first)-incident.x).toDouble().pow(2) + (center.second-incident.y).toDouble().pow(2)) <=r
    }
}