package com.typeline.classes

import com.typeline.LineFunction
import com.typeline.LineObject
import com.typeline.LineType

class BooleanClass(val value: Boolean?): LineObject("Boolean") {
    init {
        functions.add(
            LineFunction("add", LineType.BOOLEAN, static = false, returns = LineType.BOOLEAN) {
                if (value==null) {
                    println("Function add in class Boolean is not static, cannot be used in a non-instantiated Boolean!")
                    return@LineFunction null
                }
                return@LineFunction convertToString((value && it[0].toBoolean()).toString())
            })
    }
}