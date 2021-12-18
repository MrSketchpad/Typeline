import Interpreter.interpret
import Interpreter.parse
import classes.IntClass
import classes.StringClass
import classes.SystemClass
import exceptions.LineException
import java.io.File
import java.util.*
import javax.sound.sampled.Line
import kotlin.jvm.JvmStatic

internal object Main {
    var objects: MutableList<LineObject> = ArrayList()
    @JvmStatic var currentLine = 0
    @JvmStatic var currentFile: File? = null
    @JvmStatic
    fun err(text: String, exc: LineException) {
        if (currentFile!=null) {
            System.err.println("${exc.NAME} at line $currentLine in ${currentFile!!.name}: $text")
        } else {
            System.err.println("${exc.NAME}: $text")
        }
    }
    @JvmStatic
    fun main(args: Array<String>) {
        objects.add(SystemClass())
        objects.add(IntClass(null))
        objects.add(StringClass(null))

        val scan = Scanner(System.`in`)
        println("TypeLine > ")
        while (true) {
            val line = scan.nextLine()
            if (line.startsWith("run ")) {
                val file = File(javaClass.getResource(line.substring(4))!!.toURI())
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
            println("TypeLine > ")
        }
    }
}