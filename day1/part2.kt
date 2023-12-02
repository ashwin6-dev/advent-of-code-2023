package day1

import java.io.File

fun getCalibrationValue2(s: String): Int {
    val numberMap: Map<String, Int> =
        mapOf(
            "0" to 0,
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

    var first: Int = -1
    var last: Int = -1

    for (i in 0 until s.length) {
        for (j in i .. s.length) {
            val substring = s.substring(i, j)
            if (numberMap.containsKey(substring)) {
                if (first == -1) {
                    first = numberMap.get(substring) ?: -1
                }

                last = numberMap.get(substring) ?: -1
            }
        }
    }

    return 10*first + last
}

fun main() {
    val lines: List<String> = File("day1/input.txt").readLines()
    val sum: Int = lines.map(::getCalibrationValue2).sum()

    println (sum)
}