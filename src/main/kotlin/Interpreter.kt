import LineObject.Companion.getFromName
import classes.IntClass
import classes.StringClass
import java.util.ArrayList
import javax.sound.sampled.Line

internal object Interpreter {
    @JvmStatic
    fun interpret(text: String) {
        val split = text.split("\\.".toRegex()).toTypedArray()
        var currentParent: LineObject? = null
        for ((index, obj) in split.withIndex()) {
            if (currentParent == null) {
                currentParent = getFromName(obj)
                if (currentParent==null) {
                    when (LineType[obj]) {
                        LineType.INT -> {
                            currentParent = IntClass(obj.toInt())
                        }
                        LineType.STRING -> {
                            currentParent = StringClass(obj)
                        }
                        LineType.NOTHING -> {
                            println("Nothing cannot be used as an object!")
                        }
                    }
                    if (currentParent == null) {
                        println("Class $obj not found.")
                        return
                    }
                }
            } else {
                var args: String
                try {
                    args = obj.substring(obj.indexOf("(") + 1, obj.indexOf(")"))
                } catch (e: Exception) {
                    println("Unclosed parenthesis when calling function $obj.")
                    return
                }
                val splitArgs = args.split(",".toRegex()).toTypedArray()
                val actualArgs: MutableList<LineType> = ArrayList()

                val formattedArgs = mutableListOf<String>()

                for (unformatted in splitArgs) {
                    var arg = unformatted
                    if (unformatted.isNotEmpty() && unformatted.toCharArray()[0] == ' ') {
                        arg = unformatted.removeRange(0..1)
                    }
                    try {
                        actualArgs.add(LineType[arg]!!)
                    } catch (e: Exception) {}

                    formattedArgs.add(LineType[arg]!!.toLineString(arg))
                }
                if (!currentParent.hasFunction(obj, actualArgs)) {
                    println("The class ${currentParent.name} has no function ${obj.substring(0, obj.indexOf("("))} with arguments" +
                            " ${actualArgs.toString().replace("[", "").replace("]", "")}.")
                    return
                }
                val func = currentParent.getFunction(obj, actualArgs)
                if (actualArgs == func?.args) {
                    split[index] = func.action(formattedArgs.toList()).toString()
                } else {
                    println(
                        "Incorrect arguments for function " + func?.name + ": required " + func?.args.toString()
                            .replace("[", "").replace("]", "") + ", found " + actualArgs.toString().replace("[", "")
                            .replace("]", "") + "."
                    )
                }
            }
        }
    }
}