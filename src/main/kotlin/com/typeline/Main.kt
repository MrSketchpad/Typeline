package com.typeline

import com.typeline.Interpreter.parse
import com.typeline.classes.BooleanClass
import com.typeline.classes.IntClass
import com.typeline.classes.StringClass
import com.typeline.classes.SystemClass
import com.typeline.exceptions.LineException
import java.io.File
import java.util.*

internal object Main {
    var objects: MutableList<LineObject> = ArrayList()
    @JvmStatic var currentLine = 0
    @JvmStatic var currentFile: File? = null
    @JvmStatic
    fun err(exc: LineException) {
        if (currentFile !=null) {
            System.err.println("${exc::class.java.name} at line $currentLine in ${currentFile!!.name}: ${exc.msg}")
        } else {
            System.err.println("${exc::class.java.name}: ${exc.msg}")
        }
    }
    @JvmStatic
    fun main(args: Array<String>) {
        objects.add(SystemClass())
        objects.add(IntClass(null))
        objects.add(StringClass(null))
        objects.add(BooleanClass(null))

        val scan = Scanner(System.`in`)
        print("TypeLine > ")
        while (true) {
            val line = scan.nextLine()
            if (line.startsWith("run ")) {
                val file = File(javaClass.classLoader.getResource(line.substring(4))!!.toURI())
                if (file.exists()) {
                    currentFile = file
                    for (fileLine in file.readLines()) {
                        currentLine += 1
                        parse(fileLine)
                    }
                } else {
                    println("$file not found.")
                }
            } else {
                parse(line)
            }
            print("TypeLine > ")
        }
    }
}