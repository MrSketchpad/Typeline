package com.typeline.exceptions

import com.typeline.Main

open class LineException(val msg: String) {
    fun throwLine() {
        Main.err(this)
    }
    companion object {
        infix fun throwLine(exc: LineException) {
            exc.throwLine()
        }
    }
}