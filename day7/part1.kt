package day7

import java.io.File

enum class Hand {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND
}

fun getHandType(hand: String): Hand {
    val counts: MutableMap<Char, Int> = mutableMapOf()

    for (card in hand) {
        val cardCount: Int = counts.get(card) ?: 0
        counts.put(card, cardCount + 1)
    }

    val countsString: String = counts.values.toList().sortedDescending().joinToString(separator = "")
    if (countsString == "5") {
        return Hand.FIVE_OF_A_KIND
    }else if (countsString == "41") {
        return Hand.FOUR_OF_A_KIND
    }else if (countsString == "32") {
        return Hand.FULL_HOUSE
    }else if (countsString == "311") {
        return Hand.THREE_OF_A_KIND
    }else if (countsString == "221") {
        return Hand.TWO_PAIR
    }else if (countsString == "2111") {
        return Hand.ONE_PAIR
    }

    return Hand.HIGH_CARD
}

val compareHands = object: Comparator<String> {
    override fun compare (hand1: String, hand2: String): Int {
        val handType1: Hand = getHandType(hand1)
        val handType2: Hand = getHandType(hand2)

        if (handType1.ordinal > handType2.ordinal) {
            return 1
        }else if (handType2.ordinal > handType1.ordinal) {
            return -1
        }

        val cardRanking = "23456789TJQKA"

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

fun totalWinnings(handsAndBids: List<String>, comparator: Comparator<String>): Int {
    var handToBid = handsAndBids.map { it.split(" ")[0] to it.split(" ")[1].toInt() }.toMap()
    var hands = handsAndBids.map { it.split(" ")[0] }
    hands = hands.sortedWith(comparator)
    var winnings = 0

    for (i in 0 until hands.size) {
        val hand = hands[i]
        winnings += (i + 1) * (handToBid.get(hand) ?: 0)
    }

    return winnings
}
fun main() {
    val hands: List<String> = File("day7/input.txt").readLines()
    val winnings = totalWinnings(hands, compareHands)

    println(winnings)
}