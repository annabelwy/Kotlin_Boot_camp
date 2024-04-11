package src.exercise4.src.main.kotlin


fun main() {
    println("Type a response code:")
    val num = readln().toInt()
    val type = chooseType(num)
    type.printRes()
}

fun chooseType(num:Int):Answer{
    return when(num){
        200,201-> Answer.Success(200)
        1001,1002 -> Answer.Error.KnownError(num)
        else -> Answer.Error.UnknownError(num)
    }
}