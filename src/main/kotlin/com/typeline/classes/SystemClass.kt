package com.typeline.classes

import com.typeline.LineFunction
import com.typeline.LineObject
import com.typeline.LineType
import com.typeline.Main
import com.typeline.exceptions.ValidateException

class SystemClass: LineObject("System",
    LineFunction("println", LineType.STRING) {
        println(it[0])
    },
    LineFunction("println", LineType.INT) {
        println(it[0])
    },
    LineFunction("print", LineType.STRING) {
        print(it[0])
    },
    LineFunction("print", LineType.INT) {
        print(it[0])
    },
    LineFunction("println", LineType.BOOLEAN) {
        println(it[0])
    },
    LineFunction("print", LineType.BOOLEAN) {
        print(it[0])
    },
    LineFunction("readln", LineType.STRING, returns = LineType.STRING) {
        println(it[0])
        return@LineFunction convertToString(readLine()!!)
    },
    LineFunction("readln", returns = LineType.STRING) {
        return@LineFunction convertToString(readLine()!!)
    },
    LineFunction("validate", returns = LineType.BOOLEAN) {
        val value = it[0].toBoolean()
        if (value) {
            return@LineFunction null
        } else {
            Main.err(ValidateException("validation unsuccessful."))
            return@LineFunction null
        }
    })