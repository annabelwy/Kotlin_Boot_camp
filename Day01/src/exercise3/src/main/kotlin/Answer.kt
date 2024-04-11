package src.exercise4.src.main.kotlin

import java.util.*

sealed class Answer {
    abstract val type: String
    abstract val code: Int

    open fun printRes() {
        println(type)
        println("\tCode: $code")
    }

    data class Success(override val code: Int) : Answer() {
        override val type = "Success"
        override fun printRes() {
            super.printRes()
            println("\tMessage: The request processed successfully")
        }
    }

    sealed class Error() : Answer() { // абстрактные методы answer можно переписать в наследнике наследнике

//        open val title = "Error code: $code"
        open val title: String
            get() = "Error code: $code"  // get  динамически метод, то есть всё может измениться и все перемнные в нем
        open val description: String
            get() = "$type. Please, try again later"


        data class KnownError(override val code: Int) : Answer.Error() {
            private val infoCode = InternalErrorType.fromCode(code)
            override val title = infoCode.title
            override val type: String = infoCode.title.split(" ").joinToString(separator = "") { it ->
                it.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
            }

            override fun printRes() {
                super.printRes()
                println("\tTitle: $title")
                println("\tDescription: $title. Try later.")
            }
        }

        data class UnknownError(override val code: Int) : Answer.Error() {
            override val type = "Unknown Error"
            override fun printRes() {
                super.printRes()
                println("\tTitle: $title")
                println("\tDescription: $description")
            }
        }
    }
}

