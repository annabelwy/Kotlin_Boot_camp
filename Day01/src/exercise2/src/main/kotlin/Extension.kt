package main.kotlin

fun String.mask():String {
    val res = StringBuilder(this.filter(Char::isDigit))
    if (res.length==11) {
        return when {
            res.substring(1, 4) == "800" -> "8 (${res.substring(1, 4)}) ${res.substring(4, 7)} ${res.substring(7, 9)} ${res.substring(9, 11)}"

            else  -> "+7 ${res.substring(1, 4)} ${res.substring(4, 7)} ${res.substring(7, 9)} ${res.substring(9, 11)}"
        }
    }
    else return this
}