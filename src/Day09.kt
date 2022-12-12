import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val visitedCoords = mutableSetOf<Pair<Int, Int>>()
        var hX = 0
        var hY = 0
        var tX = 0
        var tY = 0

        visitedCoords.add(0 to 0)

        input.map {
            val (dir, distStr) = it.split(" ")
            val dist = distStr.toInt()
            for(i in 0 until dist) {
                val currX = hX
                val currY = hY

                when (dir) {
                    "U"-> {
                        hY += 1
                    }"D"  -> {
                        hY -= 1
                    } "L" -> {
                        hX -= 1
                    } "R" -> {
                        hX += 1
                    } else -> {
                        throw Exception("input is invalid")
                    }
                }

                // T replaces where H was if H gets 2 spots away from T
                if(abs(hX-tX) + abs(hY-tY) > 2 || abs(hX-tX) == 2 || abs(hY-tY) == 2 ) {
                    tX = currX
                    tY = currY
                }

                visitedCoords.add(tX to tY)
            }
        }
        return visitedCoords.size
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
