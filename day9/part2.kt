package day9

import java.io.File

fun main() {
    val nodes: List<String> = File("day9/input.txt").readLines()
    val values = nodes.map(::readNumbers).map { it.reversed() } .map(::extrapolate).sum()

    println (values)
}