package day4

import java.io.File
import kotlin.math.log2

val cache: MutableMap<Int, Int> = mutableMapOf()
fun cardsWonFrom(cards: List<String>, cardIndex: Int): Int {
    if (cardIndex >= cards.size) return 0

    return cache.get(cardIndex) ?: run {
        val card = cards[cardIndex]
        val score = getCardScore(card)
        val numOfCardsWon = if (score <= 1) score else log2(score.toDouble()).toInt() + 1
        val indexes = cardIndex + 1..cardIndex + numOfCardsWon
        val result = numOfCardsWon + indexes.map { cardsWonFrom(cards, it) }.sum()
        cache.put(cardIndex, result)
        return result
    }
}

fun processCards(cards: List<String>): Int {
    val indexes = 0 until cards.size
    return cards.size + indexes.map { cardsWonFrom(cards, it) }.sum()
}
fun main() {
    val cards: List<String> = File("day4/input.txt").readLines()
    val sum: Int = processCards(cards)
    println (sum)
}