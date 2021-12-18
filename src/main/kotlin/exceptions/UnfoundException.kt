package exceptions

class UnfoundException(msg: String): LineException(msg) {
    override val NAME: String
        get() = "SyntaxException"
}