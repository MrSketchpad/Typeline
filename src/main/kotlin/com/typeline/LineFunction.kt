package com.typeline

class LineFunction(var name: String, vararg tempArgs: LineType = arrayOf(LineType.NOTHING), val static: Boolean = true, val returns: LineType = LineType.NOTHING, val action: (args: List<String>) -> Any?) {
    val args: List<LineType>
    init {
        this.args = tempArgs.asList()
    }
}