package src.exercise4.src.main.kotlin

import java.util.StringJoiner

//import kotlin.collections.EmptyMap.entries

enum class InternalErrorType(val code:Int,val title:String) {
    ER_1000(1000,"The user is not identified"),
    ER_1001(1001, "The session is expired"),
    ER_1002(1002, "No connection"),
    ER_1003(1003,"The device has failed the verification");

    companion object {
        fun fromCode(code: Int): InternalErrorType {
            return values().find { it.code == code } ?: throw IllegalArgumentException("Invalid code: $code")
        }
    }
}