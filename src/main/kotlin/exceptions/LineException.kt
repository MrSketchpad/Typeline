package exceptions

open class LineException(val msg: String) {
    open val NAME = "Exception"
    fun throwLine() {
        Main.err(msg, this)
    }
    companion object {
        infix fun throwLine(exc: LineException) {
            exc.throwLine()
        }
    }
}