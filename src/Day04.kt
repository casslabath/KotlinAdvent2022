fun main() {
    fun rangesContained(firstRange: ClosedRange<Int>, secondRange: ClosedRange<Int>): Boolean {
        if(
        // The first pair is contained in the second
            firstRange.start >= secondRange.start && firstRange.endInclusive <= secondRange.endInclusive
            // The first pair is contained in the second
            || firstRange.start <= secondRange.start && firstRange.endInclusive >= secondRange.endInclusive) {
            return true
        }
        return false
    }

    fun rangesIntersect(firstRange: ClosedRange<Int>, secondRange: ClosedRange<Int>): Boolean {
        for(i in firstRange.start..firstRange.endInclusive) {
            if(secondRange.contains(i)) {
                return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        var containedPairs = 0

        input.map {line ->
            val pairs = line.split(",").map {
                it.split("-")
            }
            val firstRange = pairs[0][0].toInt()..pairs[0][1].toInt()
            val secondRange = pairs[1][0].toInt()..pairs[1][1].toInt()

            if(rangesContained(firstRange, secondRange)) {
                containedPairs++
            }
        }

        return containedPairs
    }

    fun part2(input: List<String>): Int {
        var containedPairs = 0

        input.map {line ->
            val pairs = line.split(",").map {
                it.split("-")
            }
            val firstRange = pairs[0][0].toInt()..pairs[0][1].toInt()
            val secondRange = pairs[1][0].toInt()..pairs[1][1].toInt()

            if(rangesIntersect(firstRange, secondRange)) {
                containedPairs++
            }
        }

        return containedPairs
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
