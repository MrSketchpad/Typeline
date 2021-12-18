import exceptions.LineException
import exceptions.LineException.Companion.throwLine
import exceptions.SyntaxException
import java.lang.Exception

enum class LineType(val type: Class<*>, val strValue: String) {
    STRING(String::class.java, "String"),
    INT(Int::class.java, "Int"),
    NOTHING(Unit::class.java, "Nothing")
    ;

    override fun toString(): String {
        return strValue
    }

    fun toLineString(prev: String): String {
        return when (this) {
            INT -> {
                prev
            }
            STRING -> {
                if (prev=="\"\"") {
                    ""
                } else {
                    prev.substring(1, prev.length-1)
                }
            }
            NOTHING -> {
                "Nothing"
            }
        }
    }

    companion object {
        operator fun get(obj: Any): LineType? {
            var newObj: Any? = null
            try {
                newObj = obj.toString()
                if (newObj.toString()=="") {
                    return NOTHING
                } else if (newObj.toString().contains("\"")) {
                    return if (newObj.toString().indexOf("\"") != newObj.toString().lastIndexOf("\"")) {
                        STRING
                    } else {
                        throwLine(SyntaxException("Unclosed parenthesis in string."))
                        NOTHING
                    }
                }
                newObj = obj.toString().toInt()
            } catch (e: Exception) {}
            if (newObj!!::class == String::class) {
                return STRING
            } else if (newObj::class == Int::class) {
                return INT
            }
            return null
        }
    }
}