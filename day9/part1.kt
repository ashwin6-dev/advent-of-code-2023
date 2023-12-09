package day9

import java.io.File

fun readNumbers(nums: String): List<Long> =  nums.split(" ").map { it.toLong() }

fun getDiffs(nums: List<Long>): List<Long> {
    var diffs: List<Long> = listOf()

    for (i in 1 until nums.size) {
        val diff = nums[i] - nums[i - 1]
        diffs += listOf(diff)
    }

    return diffs
}
fun extrapolate(nums: List<Long>): Long {
    val allZero = nums.foldRight(true, { num, acc -> num == 0.toLong() && acc })
    if (allZero) return 0

    val diffs = getDiffs(nums)
    return nums.last() + extrapolate(diffs)
}
fun main() {
    val nodes: List<String> = File("day9/input.txt").readLines()
    val values = nodes.map(::readNumbers).map(::extrapolate).sum()

    println (values)
}