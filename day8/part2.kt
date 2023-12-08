package day8

import java.io.File

fun loopSize(map: Map<String, Pair<String, String>>, instructions: String, nodeStart: String): Int {
    var nodeName: String = nodeStart
    var instructionPtr = 0
    var steps = 0
    var count = 0

    while (nodeName.last() != 'Z' || count != 2) {
        val instruction = instructions[instructionPtr]
        val node = map[nodeName]

        nodeName = if (instruction == 'L') {
            node?.first.toString()
        }else {
            node?.second.toString()
        }

        instructionPtr += 1
        instructionPtr = instructionPtr % (instructions.length)
        steps += 1

        if (nodeName.last() == 'Z') {
            if (count == 0) steps = 0
            count += 1
        }
    }

    return steps
}

fun gcd(a: Long, b: Long): Long {
    if (a == 0.toLong()) return b

    return gcd(b % a, a)
}
fun lcm(nums: List<Long>): Long {
    return nums.reduce { acc, it -> (acc * it) / gcd(acc, it) }
}
fun doParallelInstructions(map: Map<String, Pair<String, String>>, instructions: String): Long {
    val nodeNames = map.keys.filter { it.last() == 'A' }
    val loopSizes = nodeNames.map { loopSize(map, instructions, it).toLong() }

    return lcm(loopSizes)
}
fun main() {
    val nodes: List<String> = File("day8/input.txt").readLines()
    val map = buildMap(nodes.subList(2, nodes.size))
    val steps = doParallelInstructions(map, nodes[0])

    println(steps)
}