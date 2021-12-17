package classes

import LineFunction
import LineObject

class IntClass(val value: Int?): LineObject("Int") {
    init {
        functions.add(
            LineFunction("add", LineType.INT, static = false, returns = LineType.INT) {
                if (value==null) {
                    println("Function add in class Int is not static, cannot be used in a non-instantiated Int!")
                    return@LineFunction null
                }
                return@LineFunction value+it[0].toInt()
            }
        )
    }
}