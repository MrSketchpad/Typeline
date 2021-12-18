package com.typeline

import com.typeline.LineObject.Companion.getFromName
import com.typeline.classes.BooleanClass
import com.typeline.classes.IntClass
import com.typeline.classes.StringClass
import com.typeline.exceptions.IllegalArgumentException
import com.typeline.exceptions.LineException.Companion.throwLine
import com.typeline.exceptions.SyntaxException
import com.typeline.exceptions.UnfoundException

internal object Interpreter {

    @JvmStatic
    fun parse(text: String) {
        val split = text.split("\\.".toRegex()).toTypedArray()
        val blacklisted = mutableListOf<Int>()
        for ((index, obj) in split.withIndex()) {
            if (blacklisted.contains(index)) {
                continue
            }
            if (obj.contains("(")) {
                var actual = obj
                if (index!=split.size-1) {
                    actual += "."+split[index+1]
                    blacklisted.add(index+1)
                }

                var actualIndex = index
                var working = false
                var cut = ""

                while (!working) {
                    try {
                        cut = actual.substring(actual.indexOf("(")+1, actual.lastIndexOf(")"))
                        working = true
                    } catch (e: Exception) {
                        actualIndex += 1
                        actual += "."
                        actual += split[actualIndex]
                        blacklisted.add(actualIndex)
                    }
                }

                actual = actual.replaceRange(actual.indexOf("(")+1..actual.lastIndexOf(")"), interpret(cut).toString()) + ")"
                split[index] = actual
            }
        }
        val builder = StringBuilder()
        for ((index, newObj) in split.withIndex()) {
            builder.append(newObj).append(
                if (index==split.size-1) ""
                else "."
            )
        }
        interpret(builder.toString())
    }

    @JvmStatic
    fun interpret(text: String): Any? {
        val split = text.split("\\.".toRegex()).toTypedArray()
        var currentParent: LineObject? = null
        for (obj in split) {
            if (split.size==1) {
                return text
            }
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
                        LineType.BOOLEAN -> {
                            currentParent = BooleanClass(obj.toBoolean())
                        }
                        LineType.NOTHING -> {
                            throwLine(SyntaxException("Nothing cannot be used as an object!"))
                        }

                    }
                    if (currentParent == null) {
                        throwLine(UnfoundException("Class $obj not found."))
                        return ""
                    }
                }
            } else {
                var args: String
                try {
                    args = obj.substring(obj.indexOf("(") + 1, obj.indexOf(")"))
                } catch (e: Exception) {
                    throwLine(SyntaxException("Unclosed parenthesis when calling function $obj."))
                    return ""
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
                    throwLine(
                        UnfoundException("The class ${currentParent.name} has no function ${obj.substring(0, obj.indexOf("("))}(" +
                            "${actualArgs.toString().replace("[", "").replace("]", "")})."))
                    return ""
                }
                val func = currentParent.getFunction(obj, actualArgs)
                if (actualArgs == func?.args) {
                    return func.action(formattedArgs.toList())
                } else {
                    throwLine(
                        IllegalArgumentException(
                        "Incorrect arguments for function " + func?.name + ": required " + func?.args.toString()
                            .replace("[", "").replace("]", "") + ", found " + actualArgs.toString().replace("[", "")
                            .replace("]", "") + "."
                    ))
                }
            }
        }
        return ""
    }
}