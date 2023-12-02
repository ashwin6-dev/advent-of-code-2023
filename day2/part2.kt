package day2

import kotlin.math.max
import java.io.File

fun getPower(s: String): Int {
    val gameString: String = s.split(":")[1]
    val rounds: List<Pair<Int, String>> = gameString.split(";").
    map { x ->
        x.split(",").map {
            val parts = it.trim().split(" ")
            Pair(parts[0].toInt(), parts[1])
        }
    }.flatten()

    var red: Int = 0
    var blue: Int = 0
    var green: Int = 0

    for (round in rounds) {
        val colour = round.second
        val qty = round.first

        if (colour == "red") {
            red = max(red, qty)
        }else if (colour == "blue") {
            blue = max(blue, qty)
        }else if (colour == "green") {
            green = max(green, qty)
        }
    }

    return red * blue * green
}

fun main() {
    val lines: List<String> = File("day2/input.txt").readLines()
    val sum: Int = lines.map(::getPower).sum()
    println (sum)
}