package day4

import java.io.File

fun getCardScore(card: String): Int {
    val listsString: String = card.split(":")[1]
    val lists: List<String> = listsString.split("|")

    val winningNumbers: List<String> = lists[0].split(" ").filter { it.isNotEmpty() }
    val cardNumbers: List<String> = lists[1].split(" ").filter { it.isNotEmpty() }

    var points = 0

    for (number in cardNumbers) {
        if (winningNumbers.contains(number)) {
            points = if (points == 0) 1 else points * 2
        }
    }

    return points
}

fun main() {
    val cards: List<String> = File("day4/input.txt").readLines()
    val sum: Int = cards.map(::getCardScore).sum()
    println (sum)
}