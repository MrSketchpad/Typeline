package exceptions

class IllegalArgumentException(msg: String): LineException(msg) {
    override val NAME: String
        get() = "IllegalArgumentException"
}