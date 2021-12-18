package com.typeline.classes

import com.typeline.LineFunction
import com.typeline.LineObject
import com.typeline.LineType

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
        functions.add(
            LineFunction("product", LineType.INT, static = false, returns = LineType.INT) {
                if (value==null) {
                    println("Function product in class Int is not static, cannot be used in a non-instantiated Int!")
                    return@LineFunction null
                }
                return@LineFunction value*it[0].toInt()
            }
        )
        functions.add(
            LineFunction("div", LineType.INT, static = false, returns = LineType.INT) {
                if (value==null) {
                    println("Function div in class Int is not static, cannot be used in a non-instantiated Int!")
                    return@LineFunction null
                }
                return@LineFunction value/it[0].toInt()
            }
        )
        functions.add(
            LineFunction("sub", LineType.INT, static = false, returns = LineType.INT) {
                if (value==null) {
                    println("Function sub in class Int is not static, cannot be used in a non-instantiated Int!")
                    return@LineFunction null
                }
                return@LineFunction value-it[0].toInt()
            }
        )
    }
}