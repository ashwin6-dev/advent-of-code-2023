package day5

import java.io.File

fun applyInverseMap(n: Long, map: List<List<Long>>): Long {
    var value = n

    for (line in map) {
        val sourceStart = line[0]
        val destStart = line[1]
        val range = line[2]

        if (value >= destStart && value <= destStart + range) {
            value = sourceStart + (value - destStart)
            break
        }
    }

    return value
}

fun locToSeed(loc: Long, maps: Map<String, List<List<Long>>>): Long {
    var seed = loc

    for (entry in maps.entries.reversed().iterator()) {
        val map = entry.value
        seed = applyInverseMap(seed, map)
    }

    return seed
}

fun isNumInSeed(n: Long, seeds: List<Long>): Boolean {
    val nums = seeds.size

    for (i in 0 until nums / 2) {
        val start = seeds[i * 2]
        val range = seeds[i * 2 + 1]

        if (n >= start && n < start + range) {
            return true
        }
    }

    return false
}

fun getLowestLocationFromSeedRanges(maps: List<String>): Long {
    val mapStrings: Map<String, List<String>> = getMapStrings(maps)
    val builtMaps: Map<String, List<List<Long>>> = mapStrings.mapValues { buildMap(it.value) }
    val seeds: List<Long> = getSeeds(maps)

    var loc: Long = 0
    var seed = locToSeed(loc, builtMaps)

    while (!isNumInSeed(seed, seeds)) {
        loc += 1
        seed = locToSeed(loc, builtMaps)
    }

    return loc
}

fun main() {
    val maps: List<String> = File("day5/input.txt").readLines()
    val loc: Long = getLowestLocationFromSeedRanges(maps)

    println (loc)
}