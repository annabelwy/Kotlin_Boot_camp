import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
fun main(args: Array<String>) {
    val x1= vvod('x',1)
    val y1 = vvod('y',1)
    val r1  = vvod('r',1)
    val x2  = vvod('x',2)
    val y2  = vvod('y',2)
    val r2  = vvod('r',2)
    val d =  sqrt ((x2-x1).pow(2) + (y2-y1).pow(2))
    val sum = r1+r2
    val sub = abs(r1-r2)
    println("Result: " + logic(r1,r2,d, sum, sub))
}

fun vvod (arg:Char, num:Int ):Float{
    var x: Float? // readline может принять null
    try {
        println("Input " + arg + num +":")
        x =  readLine()?.toFloat() // сразу исключение если не флот, дальше в иф не пойдет если радиус
        if (arg == 'r' && x!!<=0f) {
            throw Exception()
        }
    } catch (e:Exception) {
        println("Couldn't parse a number. Please, try again")
        x = vvod(arg, num)
    }
    return x!! //возвращает точно не null
}

fun logic(r1:Float, r2:Float, d:Float, sum:Float, sub:Float):String {
    var s = "the circles "
    if ( d == sum || d ==sub) {
        s+="touch" //касаются
    }
    else if (d==0f && sum == r1*2){
        s+="coincide" // совпадают
    }
    else if (0<d && d<sub){
        s+="inside" // внутри друг друга
    }
    else if (sub<d && d<sum) {
        s+="intersect" // пересекаются
    }
    else
    {
        s+="do not intersect" // не пересекаются
    }
    return s
}