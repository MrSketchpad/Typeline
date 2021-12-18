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
    LineFunction("print", LineType.STRING) {
        print(it[0])
    },
    LineFunction("print", LineType.INT) {
        print(it[0])
    },
    LineFunction("readln", LineType.STRING, returns = LineType.STRING) {
        println(it[0])
        return@LineFunction convertToString(readLine()!!)
    },
    LineFunction("readln", returns = LineType.STRING) {
        return@LineFunction convertToString(readLine()!!)
    })