package day7

import java.io.File

fun evalJ(hand: String): String {
    val counts: MutableMap<Char, Int> = mutableMapOf()

    for (card in hand) {
        if (card != 'J') {
            val cardCount: Int = counts.get(card) ?: 0
            counts.put(card, cardCount + 1)
        }
    }

    var maxCount = 0
    var maxCard = ""

    for (entry in counts.entries) {
        val card = entry.key
        val count = entry.value

        if (count > maxCount) {
            maxCard = card.toString()
            maxCount = count
        }
    }

    if (maxCard == "") return hand

    return hand.replace("J", maxCard)
}

val compareHandsWithJ = object: Comparator<String> {
    override fun compare (hand1: String, hand2: String): Int {
        val handType1: Hand = getHandType(evalJ(hand1))
        val handType2: Hand = getHandType(evalJ(hand2))

        if (handType1.ordinal > handType2.ordinal) {
            return 1
        }else if (handType2.ordinal > handType1.ordinal) {
            return -1
        }

        val cardRanking = "J23456789TQKA"

        for (i in 0 until hand1.length) {
            val card1 = hand1[i]
            val card2 = hand2[i]

            if (cardRanking.indexOf(card1) > cardRanking.indexOf(card2)) {
                return 1
            }else if (cardRanking.indexOf(card2) > cardRanking.indexOf(card1)) {
                return -1
            }
        }

        return 1
    }
}
fun main() {
    val hands: List<String> = File("day7/input.txt").readLines()
    val winnings = totalWinnings(hands, compareHandsWithJ)

    println(winnings)
}