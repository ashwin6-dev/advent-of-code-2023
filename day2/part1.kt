package day2

import java.io.File

fun isValid(s: String): Boolean {
    val gameString: String = s.split(":")[1]
    val rounds: List<Pair<Int, String>> = gameString.split(";").
            map { x ->
                x.split(",").map {
                    val parts = it.trim().split(" ")
                    Pair(parts[0].toInt(), parts[1])
                }
            }.flatten()

    val REDMAX: Int = 12
    val BLUEMAX: Int = 14
    val GREENMAX: Int = 13

    for (round in rounds) {
        val colour = round.second
        val qty = round.first

        if (colour == "red" && REDMAX < qty) {
            return false
        }else if (colour == "blue" && BLUEMAX < qty) {
            return false
        }else if (colour == "green" && GREENMAX < qty) {
            return false
        }
    }

    return true
}

fun getGameNo(s: String): Int {
    return s.split(":")[0].split(" ")[1].toInt()
}
fun main() {
    val lines: List<String> = File("day2/input.txt").readLines()
    val filteredLines: List<String> = lines.filter(::isValid)
    val sum: Int = filteredLines.map(::getGameNo).sum()
    println (sum)
}