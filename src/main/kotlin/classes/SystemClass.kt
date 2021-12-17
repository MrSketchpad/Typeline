package classes

import LineFunction
import LineObject

class SystemClass: LineObject("System",
    LineFunction("println", LineType.STRING) {
        println(it[0])
    },
    LineFunction("println", LineType.INT) {
        println(it[0])
    },
    LineFunction("readln", LineType.STRING, returns = LineType.STRING) {
        println(it[0])
        return@LineFunction readLine()!!
    },
    LineFunction("readln", returns = LineType.STRING) {
        return@LineFunction readLine()!!
    })