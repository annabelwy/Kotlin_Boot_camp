package main.kotlin

import Zone
import data.Incident

class Triangular(val f: Pair<Int, Int>, val s: Pair<Int, Int>, val t:Pair<Int, Int> ): Zone() {
    override val a = "triangular"
    override val phoneNum = "89374016143"
    override fun isZone(incident: Incident): Boolean {

        val result1 = getMulti(s.first, f.first, incident.x, s.second, f.second, incident.y)
        val result2 = getMulti(t.first, s.first, incident.x, t.second, s.second, incident.y)
        val result3 = getMulti(f.first, t.first, incident.x, f.second, t.second, incident.y)
        return ((result1 >= 0) xor (result2 >= 0) xor (result3 >= 0)) //xor возвращает true, когда операнды имеют разные знаки, но я поставила не
    }

    private fun getMulti(x2:Int, x1:Int, x0:Int, y2:Int, y1:Int, y0:Int):Int{
        return (x1-x0)*(y2-y1)-(x2-x1)*(y1-y0)
    }
}