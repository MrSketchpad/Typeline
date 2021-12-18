package exceptions

class SyntaxException(msg: String): LineException(msg) {
    override val NAME: String
        get() = "SyntaxException"
}