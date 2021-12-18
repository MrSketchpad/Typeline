package com.typeline

import com.typeline.Main.objects

open class LineObject(var name: String, vararg tempFunctions: LineFunction) {
    val functions: MutableList<LineFunction>

    init {
        this.functions = tempFunctions.asList().toMutableList()
    }

    fun hasFunction(name: String, args: List<LineType>): Boolean {
        return getFunction(name, args)!=null
    }

    fun getFunction(name: String, args: List<LineType>): LineFunction? {
        try {
            for (function in functions) {
                if (name.substring(0, name.indexOf("(")) == function.name && function.args == args) {
                    return function
                }
            }
        } catch (e: Exception) {
            println("Property $name not found.")
        }
        return null
    }
    companion object {
        fun getFromName(name: String): LineObject? {
            for (obj in objects) {
                if (obj.name == name) {
                    return obj
                }
            }
            return null
        }
        fun convertToString(str: String) = "\""+str+"\""
        fun removeParenths(str: String): String {
            return str.replaceFirst("\"", "").removeRange(str.length-2, str.length-1)
        }
    }
}