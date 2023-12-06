package day6

import java.io.File


/*
* This function works by using the quadratic nature of this problem
* Given a time and distance, need to find number of positive integer "x" s.t
* x * (time - x) > distance
* x * (time - x) is a negative quadratic graph with maximum point at x = time / 2
* Therefore, we can start a search of two pointers that start at x = (time / 2) - 1 and x = (time / 2) + 1
* and decrement/increment each pointer respectively till its respective distance value drops below the given distance
* */
fun getWaysFast(time: Long, distance: Long): Long {
    val midTime = time / 2
    var left = midTime - 1
    var right = midTime + 1

    var valLeft = left * (time - left)
    var valRight = right * (time - right)

    while (valLeft > distance || valRight > distance) {
        if (valLeft > distance) {
            left -= 1
            valLeft = left * (time - left)
        }

        if (valRight > distance) {
            right += 1
            valRight = right * (time - right)
        }
    }


    return right - left - 1
}

fun readNumber(num: String): Long = num.replace(" ", "").toLong()

fun main() {
    val races: List<String> = File("day6/input.txt").readLines()
    val time: Long = readNumber(races[0].split(":")[1])
    val distance: Long = readNumber(races[1].split(":")[1])
    println (getWaysFast(time, distance))
}