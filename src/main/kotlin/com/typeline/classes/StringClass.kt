package com.typeline.classes

import com.typeline.LineFunction
import com.typeline.LineObject
import com.typeline.LineType

class StringClass(val value: String?): LineObject("String") {
    init {
        functions.add(
            LineFunction("add", LineType.STRING, static = false, returns = LineType.STRING) {
                if (value==null) {
                    println("Function combine in class String is not static, cannot be used in a non-instantiated String!")
                    return@LineFunction null
                }
                return@LineFunction convertToString(removeParenths(value) +it[0])
            })
    }
}