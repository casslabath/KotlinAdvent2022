import kotlin.math.abs

fun main() {

    fun MutableList<MutableList<Int>>.head(): MutableList<Int> {
        return this[0]
    }

    fun MutableList<MutableList<Int>>.tail(): MutableList<Int> {
        return this[9]
    }

    fun updatedCoords(dir: String): Pair<Int, Int> {
        return when (dir) {
            "U"-> {
                0 to 1
            }"D"  -> {
                0 to -1
            } "L" -> {
                -1 to 0
            } "R" -> {
                1 to 0
            } else -> {
                throw Exception("input is invalid")
            }
        }
    }

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

                // Update the head know
                val updatedCoords = updatedCoords(dir)
                hX += updatedCoords.first // updatedCoords x value
                hY += updatedCoords.second // updatedCoords y value

                // T replaces where H was if H gets more than one move away (up, down, left, right or diagonal)
                if(abs(hX-tX) + abs(hY-tY) == 3 || abs(hX-tX) == 2 || abs(hY-tY) == 2 ) {
                    tX = currX
                    tY = currY
                }

                visitedCoords.add(tX to tY)
            }
        }
        return visitedCoords.size
    }

    fun part2(input: List<String>): Int {
        val visitedCoords = mutableSetOf<Pair<Int, Int>>()
        val coords = mutableListOf<MutableList<Int>>()

        visitedCoords.add(0 to 0)
        for(i in 0..9) {
            coords.add(mutableListOf(0,0))
        }

        input.map {
            val (dir, distStr) = it.split(" ")
            val dist = distStr.toInt()

            for(distNum in 0 until dist) {
                // Update the Head Knot
                val updatedCoords = updatedCoords(dir)
                coords.head()[0] += updatedCoords.first // updatedCoords x value
                coords.head()[1] += updatedCoords.second // updatedCoords y value

                for(i in 1..9) {
                    // distance between current knot and the last knot after it is updated
                    val xDist = coords[i-1][0] - coords[i][0]
                    val yDist =  coords[i-1][1] - coords[i][1]

                    fun updateX() {
                        if (xDist < 0) {
                            coords[i][0]--
                        } else {
                            coords[i][0]++
                        }
                    }

                    fun updateY() {
                        if (yDist < 0) {
                            coords[i][1]--
                        } else {
                            coords[i][1]++
                        }
                    }

                    // The portion of the rope is diagonal + one away
                    if(abs(xDist) + abs(yDist) == 3) {
                        updateX()
                        updateY()
                    } else {
                        if (abs(xDist) == 2) {
                            updateX()
                        }

                        if (abs(yDist) == 2) {
                            updateY()
                        }
                    }
                }

                visitedCoords.add(coords.tail()[0] to coords.tail()[1])
            }
        }
        return visitedCoords.size
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
