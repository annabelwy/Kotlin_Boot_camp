import kotlin.math.abs
import kotlin.math.sqrt


fun main(args: Array<String>) {
    if (args.size!=0) { check_args(args[0])} else throw Exception("Not correct arg")
    println("The grouping order is: ${args[0]}")
    println("Enter a number:")
    val numb = inp()
    println("\n\nResult:")
    sort(args[0], numb)
}

fun inp():Int{
    val num = readLine()?.toIntOrNull()
    if (num==null) {
        throw IllegalArgumentException("Wrong expression")
    }
    return num
}

fun check_args(arg:String ) {
    if (arg !in arrayOf("higher", "lower")) {
        throw Exception("Not correct arg")
    }
}



fun if_prime(numb:Int):String {
    var absnum = abs(numb)
    var count = 0
    var res = ""
    if (absnum !in 0..2) {
        for (i in 2..(sqrt(absnum.toDouble())).toInt()) {
            if (absnum%i!=0) {
                count++
            }
            else {
                break
            }
        }
        if (count == (sqrt(absnum.toDouble())).toInt()-1) {
            res = " - prime"
        }
    }
    return res
}

fun sort(order:String, numb: Int) {
    var someList = mutableListOf<Int>()
    val range = take_order(order,numb)
    for (i in range) {
        someList.add(numb.toString()[i].toString().toInt())
        var full_num = someList.joinToString(separator = "")
        println( full_num+ if_prime(full_num.toInt()))
    }
}

fun take_order(order:String, numb:Int):IntProgression{
    var start = when  {
        numb<0->1
        else->0
    }
    var range:IntProgression
    if (order == "lower") {
        range = numb.toString().length-1 downTo start
    }
    else if (order == "higher"){
        range = start until numb.toString().length // когда until  - последний элемент не принимается
    }
    else {
        throw java.lang.IllegalArgumentException("Message")
    }
    return range
}

