package day3
import java.io.File

fun getAdjacentCells(schematic: List<String>, row: Int, col: Int): List<Char> {
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

    val rows: Int = schematic.size
    val cols: Int = schematic[0].length

    val adjacentCells: List<Char> = adjancentIndexes.
            filter { it.first < rows && it.second < cols && it.first >= 0 && it.second >= 0 }.
            map { schematic[it.first][it.second] }

    return adjacentCells
}

fun getPartNumbersSum(schematic: List<String>): Int {
    val rows: Int = schematic.size
    val cols: Int = schematic[0].length
    val digits: String = "0123456789"
    var sum: Int = 0

    for (row in 0 until rows) {
        var currNum: String = ""
        var isPart: Boolean = false

        for (col in 0 until cols) {
            val cell: Char = schematic[row][col]

            if (digits.contains(cell)) {
                currNum += cell
                val adjacents = getAdjacentCells(schematic, row, col)

                if (!isPart) {
                    isPart = adjacents.filter { !digits.contains(it) && it != '.' }.size > 0
                }
            }else if (isPart) {
                println (currNum)
                sum += currNum.toInt()
                currNum = ""
                isPart = false
            }else {
                currNum = ""
                isPart = false
            }
        }

        if (isPart) {
            sum += currNum.toInt()
        }
    }

    return sum
}
fun main() {
    val schematic: List<String> = File("day3/input.txt").readLines()
    val sum: Int = getPartNumbersSum(schematic)
    println (sum)
}
