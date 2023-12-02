package day1

import java.io.File

fun getCalibrationValue(s: String): Int {
    val digits: String = "0123456789"
    var first: Int = -1
    var last: Int = -1

    for (c in s) {
        if (digits.contains(c)) {
            if (first == -1) {
                first = c.digitToInt()
                last = c.digitToInt()
            }else {
                last = c.digitToInt()
            }
        }
    }

    return 10 * first + last
}

fun main() {
    val lines: List<String> = File("day1/input.txt").readLines()
    val sum: Int = lines.map(::getCalibrationValue).sum()

    println (sum)
}