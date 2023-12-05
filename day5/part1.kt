package day5

import java.io.File

fun getMapStrings(maps: List<String>): Map<String, List<String>> {
    var curMapName = ""
    var curMapList: List<String> = listOf()
    var mapsMap: List<Pair<String, List<String>>> = listOf()

    for (line in maps) {
        if (line.contains("map")) {
            if (curMapName.isNotEmpty()) {
                val pairToAdd = curMapName to curMapList
                mapsMap += listOf(pairToAdd)
            }
            curMapName = line.split(" map:")[0]
            curMapList = listOf()
        }else if (line.isNotEmpty() && curMapName.isNotEmpty()) {
            curMapList += listOf(line)
        }
    }

    val pairToAdd = curMapName to curMapList
    mapsMap += listOf(pairToAdd)

    return mapsMap.toMap()
}

fun getSeeds(maps: List<String>): List<Long> {
    val nums = maps[0].split(": ")[1]

    return nums.split(" ").map { it.toLong() }
}


fun buildMap(map: List<String>): List<List<Long>> {
    var builtMap: List<List<Long>> = listOf()

    for (line in map) {
        val intValues = line.split(" ").map { it.toLong() }
        val sourceStart: Long = intValues[1]
        val destStart: Long = intValues[0]
        val range: Long = intValues[2]

        builtMap += listOf(listOf(sourceStart, destStart, range))
    }

    return builtMap
}

fun applyMaps(seed: Long, maps: Map<String, List<List<Long>>>): Long {
    var value = seed

    for (entry in maps.entries.iterator()) {
        val map = entry.value

        for (line in map) {
            val sourceStart = line[0]
            val destStart = line[1]
            val range = line[2]

            if (value >= sourceStart && value <= sourceStart + range) {
                value = destStart + (value - sourceStart)
                break
            }
        }
    }

    return value
}

fun getLowestLocation(maps: List<String>): Long {
    val mapStrings: Map<String, List<String>> = getMapStrings(maps)
    val seeds: List<Long> = getSeeds(maps)

    val builtMaps: Map<String, List<List<Long>>> = mapStrings.mapValues { buildMap(it.value) }
    val locations: List<Long> = seeds.map { applyMaps(it, builtMaps) }


    return locations.min()
}

fun main() {
    val maps: List<String> = File("day5/input.txt").readLines()
    val loc: Long = getLowestLocation(maps)
    println (loc)
}