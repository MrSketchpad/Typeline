package classes

import LineFunction
import LineObject

class StringClass(val value: String?): LineObject("String") {
    init {
        functions.add(
            LineFunction("combine", LineType.STRING, static = false, returns = LineType.STRING) {
                if (value==null) {
                    println("Function combine in class String is not static, cannot be used in a non-instantiated String!")
                    return@LineFunction null
                }
                return@LineFunction value+it[0]
        })
    }
}