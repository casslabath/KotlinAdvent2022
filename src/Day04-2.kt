fun main() {
    operator fun IntRange.contains(other: IntRange): Boolean {
        if(this.first >= other.first && this.last <= other.last) {
            return true
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

            if(firstRange in secondRange || secondRange in firstRange) {
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

            if(firstRange.any() { it in secondRange}) {
                containedPairs++
            }
        }

        return containedPairs
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
