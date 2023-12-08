package day8

import java.io.File

fun buildNode(nodeString: String): Pair<String, Pair<String, String>> {
    val lhsRhs = nodeString.split("=").map { it.trim() }
    val nodeName = lhsRhs[0]
    val nodeValue = lhsRhs[1].substring(1, lhsRhs[1].length - 1).split(",").map { it.trim() }
    val nodeValuePair = nodeValue[0] to nodeValue[1]

    return nodeName to nodeValuePair
}

fun buildMap(nodes: List<String>): Map<String, Pair<String, String>> {
    return nodes.map(::buildNode).toMap()
}

fun doInstructions(map: Map<String, Pair<String, String>>, instructions: String): Int {
    var nodeName = "AAA"
    var instructionPtr = 0
    var steps = 0

    while (nodeName != "ZZZ") {
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
    }

    return steps
}
fun main() {
    val nodes: List<String> = File("day8/input.txt").readLines()
    val map = buildMap(nodes.subList(2, nodes.size))
    val steps = doInstructions(map, nodes[0])

    println(steps)
}