package day3

import java.io.File


fun getNumberAt(schematic: List<String>, pos: Pair<Int, Int>): Int {
    val row = pos.first
    val col = pos.second
    var colCopy = col - 1
    val digits: String = "0123456789"
    var number: String = schematic[row][col].toString()

    while (colCopy >= 0) {
        val cell = schematic[row][colCopy]

        if (digits.contains(cell)) {
            number = cell + number
        }else {
            break
        }
        colCopy -= 1
    }

    colCopy = col + 1

    while (colCopy < schematic[0].length) {
        val cell = schematic[row][colCopy]

        if (digits.contains(cell)) {
            number += cell
        }else {
            break
        }
        colCopy += 1
    }

    return number.toInt()
}

fun removeNextToIndexes(indexes: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    val disallowed: MutableList<Pair<Int, Int>> = mutableListOf()

    return indexes.filter {
        disallowed.add(it.first to it.second - 1)
        disallowed.add(it.first to it.second + 1)
        !disallowed.contains(it)
    }
}
fun getGearsSum(schematic: List<String>): Int {
    val rows: Int = schematic.size
    val cols: Int = schematic[0].length
    val digits: String = "0123456789"
    var sum: Int = 0

    for (row in 0 until rows) {
        for (col in 0 until cols) {
            val cell: Char = schematic[row][col]

            if (cell == '*') {
                val adjancentIndexes: List<Pair<Int, Int>> = listOf(
                    row - 1 to col,
                    row + 1 to col,
                    row to col - 1,
                    row to col + 1,
                    row + 1 to col + 1,
                    row + 1 to col - 1,
                    row - 1 to col + 1,
                    row - 1 to col - 1,
                )

                var adjacentDigits: List<Pair<Int, Int>> = adjancentIndexes
                    .filter { digits.contains(schematic[it.first][it.second]) }

                adjacentDigits = removeNextToIndexes(adjacentDigits)

                if (adjacentDigits.size == 2) {
                    val gearNumbers: List<Int> = adjacentDigits.map{ getNumberAt(schematic, it) }
                    sum += gearNumbers[0] * gearNumbers[1]
                }
            }
        }
    }

    return sum
}

fun main() {
    val schematic: List<String> = File("day3/input.txt").readLines()
    val sum: Int = getGearsSum(schematic)
    println (sum)
}
