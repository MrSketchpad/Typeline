import Interpreter.interpret
import classes.IntClass
import classes.StringClass
import classes.SystemClass
import java.util.*
import javax.sound.sampled.Line
import kotlin.jvm.JvmStatic

internal object Main {
    var objects: MutableList<LineObject> = ArrayList()
    @JvmStatic
    fun main(args: Array<String>) {

        objects.add(SystemClass())
        objects.add(IntClass(null))
        objects.add(StringClass(null))

        val scan = Scanner(System.`in`)
        while (true) {
            println("TypeLine > ")
            interpret(scan.nextLine())
        }
    }
}