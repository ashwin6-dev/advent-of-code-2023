package day5

import java.io.File
import kotlin.math.max
import kotlin.math.min

/*
fun applyMapToRange(map: List<List<Long>>, s: Long, r: Long): List<Pair<Long, Long>> {
    var ranges: List<Pair<Long, Long>> = listOf()
    var start = s
    var range = r

    while (range > 0) {
        var found = false
        for (line in map) {
            val sourceStart = line[0]
            val destStart = line[1]
            val lineRange = line[2]
            // 5 5 = 5 6 7 8 9
            // 7 6 = 7 8 9 10 11 12
            // 7 3 = 7 8 9
            // 10 3 = 10 11 12
            if (start >= sourceStart && start < sourceStart + lineRange) {
                val pairRange = min(range, sourceStart + lineRange - start)
                val addPair = destStart + (start - sourceStart) to pairRange
                ranges += listOf(addPair)
                range -= pairRange
                start = sourceStart + lineRange
                found = true
                break
            }
        }

        if (!found) {
            ranges += listOf(start to range)
            break
        }
    }

    return ranges
}

fun applyMapsToRange(maps: Map<String, List<List<Long>>>, s: Long, r: Long): List<Pair<Long, Long>> {
    var ranges: List<Pair<Long, Long>> = listOf(s to r)

    for (entry in maps.entries.iterator()) {
        val map = entry.value
        ranges = ranges.map { applyMapToRange(map, it.first, it.second) }.flatten()
    }

    return ranges
}

fun getLowestLocationFromSeedRanges(maps: List<String>): Long {
    val mapStrings: Map<String, List<String>> = getMapStrings(maps)
    val builtMaps: Map<String, List<List<Long>>> = mapStrings.mapValues { buildMap(it.value) }
    val seeds: List<Long> = getSeeds(maps)
    var finalRanges: List<Pair<Long, Long>> = listOf()

    for (i in 0 until seeds.size / 2) {
        val start = seeds[i * 2]
        val range = seeds[i * 2 + 1]
        finalRanges += applyMapsToRange(builtMaps, start, range)
    }

    println (finalRanges)
    return finalRanges.map { it.first }.min()
}
fun main() {
    val maps: List<String> = File("day5/input.txt").readLines()
    val loc: Long = getLowestLocationFromSeedRanges(maps)

    println (loc)
}
*/

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