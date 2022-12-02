fun main() {
    /**
     * Rock = A or X       1pt
     * Paper = B or Y      2pt
     * Scissors = C or Z   3pt
     *
     * AX beats CZ
     * BY beats AX
     * CZ beats BY
     */

    val leftResults = listOf("A","B","C")
    val rightResults = listOf("X","Y","Z")

    fun calculateWinningPoints(leftIndex: Int, rightIndex: Int): Int {
        if(rightIndex == (leftIndex + 1) % 3) {
            return 6
        } else if (rightIndex == leftIndex) {
            return 3
        }
        return 0
    }

    fun expectedMoveIndex(opponentIndex: Int, gameResultIndex: Int): Int {
        if(gameResultIndex == 0) {
            var index = opponentIndex - 1
            if (index < 0) {
                index = 2
            }
            return index
        } else if(gameResultIndex == 1) {
            return opponentIndex
        } else {
            return (opponentIndex + 1) % 3
        }
    }

    fun part1(input: List<String>): Int {
        var total = 0
        input.map {
            val results = it.split(" ")
            val opponentIndex = leftResults.indexOf(results[0])
            val yourIndex = rightResults.indexOf(results[1])

            total += yourIndex + 1 + calculateWinningPoints(opponentIndex, yourIndex)
        }
        return total
    }

    /**
     * Rock = A      1pt
     * Paper = B     2pt
     * Scissors = C  3pt
     *
     * X Lose       0pt
     * Y Draw       3pt
     * Z Win        6pt
     */
    fun part2(input: List<String>): Int {
        var total = 0
        input.map {
            val results = it.split(" ")
            val opponentIndex = leftResults.indexOf(results[0])
            val gameResultIndex = rightResults.indexOf(results[1])
            total += gameResultIndex * 3 + expectedMoveIndex(opponentIndex, gameResultIndex) + 1
        }
        return total
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
