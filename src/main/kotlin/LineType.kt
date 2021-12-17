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
                prev.substring(1, prev.length-1)
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
                if (newObj=="") {
                    return NOTHING
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