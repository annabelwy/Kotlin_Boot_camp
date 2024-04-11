import java.lang.IllegalArgumentException

fun main(args: Array<String> ) {
    var first_arg = if (args.size!=0) { check_args(args[0])} else {check_args()}
    println("Output mode: $first_arg")
    var season = input_s()
    var temp = input_t()
    println()
    print_by_season (season, first_arg,temp)
}

fun check_args(arg:String = "Celsius"):String {
    var first_arg = if (arg !in arrayOf("Kelvin", "Celsius", "Fahrenheit")) {
        "Celsius"
    }else {
        arg  // если всё ок, то вовзращаю то же число
    }
    return first_arg
}

fun input_s():String {
    var season:String? = null
    try {
        println("Enter a season - (W)inter or (S)ummer:")
        season = readLine()
        if (season=="S") {
            print("Season: Summer. ")
        }
        else if (season=="W") {
            print("Season: Winter. ")
        }
        else {
            throw IllegalArgumentException()
        }
    }catch (e:IllegalArgumentException) {
        println("Incorrect input. Enter a season:")
        input_s()
    }
    return season!!
}


fun input_t():Float {
    var t: Float?
    try {
        println("Enter a temperature:")
        t = readLine()?.toFloat()
    }
    catch (e:Exception) {
        print("Incorrect input. ")
        t = input_t()
    }
    return t!!
}

fun trans_all (cels: Float, degr: String):Pair<Float, String> {
    var final_var: Pair <Float,String> = when(degr) {
        "Celsius"-> Pair(cels,"˚C")
        "Fahrenheit" -> Pair(cels*1.8f+32, "˚F")
        "Kelvin" -> Pair(cels + 273.15f, "˚K")
        else-> TODO()
    }
 return final_var
}

fun trans_digits (cels: Float, degr: String, low_limit:Float, high_limit:Float):Unit {
    val our_temp = trans_all(cels,degr)
    val our_low_temp = trans_all(low_limit,degr)
    val our_high_temp= trans_all (high_limit, degr)
    print_strings(our_temp.first,our_low_temp.first, our_high_temp.first,our_high_temp.second)
}

fun print_strings (our_t:Float, low_t:Float, high_t:Float, measure: String):Unit {
    println("The temperature is " + our_t+ " " + measure)
    println("The comfortable temperature is from " + low_t+ " " + measure + " to " + high_t + " " + measure)
    check_comfort(our_t,low_t, high_t)
}

fun check_comfort (our_t:Float, low_t:Float, high_t:Float):Unit {
    var str = when {
        our_t in low_t..high_t -> "It's okay"
        our_t<low_t -> "Please, make it warmer by " + (low_t-our_t) + " degrees"
        else -> "Please, make it colder by " + (our_t-high_t) + " degrees"
    }
    println(str)
}

fun print_by_season (season:String, measure: String, our_t: Float) {
    if (season == "S") {
        trans_digits(our_t, measure, 22f, 25f)
    }
    else {
        trans_digits(our_t, measure, 20f, 22f)
    }
}

