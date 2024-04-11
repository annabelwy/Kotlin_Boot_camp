package main.kotlin

import Zone
import data.Incident

class Tetragon(val f: Pair<Int, Int>, val s: Pair<Int, Int> = Pair(0,0), val t:Pair<Int, Int> = Pair(0,0), var tetra:Pair<Int, Int> = Pair(0,0)) :Zone() {
    override val shape = "tetragon"
    override val phoneNum = "89555016143"
    override fun isZone(incident: Incident): Boolean {
        val divis = (s.second - t.second) * (f.first - t.first) + (t.first - s.first) * (f.second - t.second)
        val w1 = ((s.second - t.second) * (incident.x - t.first) + (t.first - s.first) * (incident.y - t.second)).toDouble() / divis
        val w2 = ((t.second - f.second) * (incident.x - t.first) + (f.first - t.first) * (incident.y - t.second)).toDouble() / divis
        val w3 = 1 - w1 - w2
        val w4 = ((f.second - s.second) * (incident.x - s.first) + (s.first - f.first) * (incident.y - s.second)).toDouble() / ((f.second - s.second) * (tetra.first - s.first) + (s.first - f.first) * (tetra.second - s.second))
        return (!((w1 > 0) xor (w2 > 0) xor (w3 > 0) xor (w4>0) xor (w1+w2+w3+w4==1.0))) || ((w1 == 0.0) || (w2 == 0.0) || (w3 == 0.0) || (w4==0.0))
    }
}
