package day6

import java.io.File
fun getWays(time: Int, distance: Int): Int {
    var ways = 0

    for (wait in 0 .. time) {
        val thisDistance = (time - wait) * wait

        if (thisDistance > distance) {
            ways += 1
        }
    }

    return ways
}

fun getNumsFromList(nums: String): List<Int> {
    return nums.
            split(" ").
            filter { it.isNotEmpty() }.
            map { it.toInt() }
}

fun getTotalWays(races: List<String>): Int {
    val times = getNumsFromList(races[0].split(":")[1])
    val distances = getNumsFromList(races[1].split(":")[1])

    val n = times.size
    var  totalWays = 0

    for (i in 0 until n) {
        val ways = getWays(times[i], distances[i])
        totalWays = if (totalWays == 0) ways else totalWays * ways
    }

    return totalWays
}
fun main() {
    val races: List<String> = File("day6/input.txt").readLines()
    val ways: Int = getTotalWays(races)
    println (ways)
}