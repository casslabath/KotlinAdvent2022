fun main() {
    val moves = mapOf(
        "A" to Move.ROCK, "X" to Move.ROCK,
        "B" to Move.PAPER, "Y" to Move.PAPER,
        "C" to Move.SCISSORS, "Z" to Move.SCISSORS)
    val movePoints = mapOf(Move.ROCK to 1, Move.PAPER to 2, Move.SCISSORS to 3)

    val winsAgainst = mapOf(Move.ROCK to Move.SCISSORS, Move.PAPER to Move.ROCK, Move.SCISSORS to Move.PAPER)
    val losesTo = mapOf(Move.ROCK to Move.PAPER, Move.PAPER to Move.SCISSORS, Move.SCISSORS to Move.ROCK)

    val gameResults = mapOf("X" to Result.LOSE, "Y" to Result.DRAW, "Z" to Result.WIN)
    val gameResultPoints = mapOf(Result.LOSE to 0, Result.DRAW to 3, Result.WIN to 6)

    /**
     * Calculate the result of the game and return the respective points.
     * Win = 6 pts
     * Draw = 3 pts
     * Lose = 0 pts
     */
    fun calculateGameResultPoints(yourMove: Move?, opponentMove: Move?): Int {
        if (winsAgainst[yourMove] == opponentMove) {
             return 6
        } else if (yourMove == opponentMove) {
            return 3
        }
        return 0
    }

    /**
     * Rules:
     * Rock = A or X       1pt
     * Paper = B or Y      2pt
     * Scissors = C or Z   3pt
     *
     * AX beats CZ
     * BY beats AX
     * CZ beats BY
     */
    fun part1(input: List<String>): Int {
        var total = 0
        input.map {
            val results = it.split(" ")
            // OpponentMove and yourMove will either be ROCK, PAPER or SCISSORS.
            val opponentMove = moves[results[0]]
            val yourMove = moves[results[1]]

            total += movePoints[yourMove]!!
            total += calculateGameResultPoints(yourMove, opponentMove)
        }
        return total
    }

    /**
     * Calculate what your move is from the game result and opponent move
     * and return the resulting points.
     */
    fun calculateYourMovePoints(gameResult: Result?, opponentMove: Move?): Int {
        if (gameResult == Result.WIN) {
            return movePoints[losesTo[opponentMove]]!!
        } else if (gameResult == Result.DRAW) {
            return movePoints[opponentMove]!!
        }
        return movePoints[winsAgainst[opponentMove]]!!
    }

    /**
     * Rules:
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
            // OpponentMove will be either ROCK, PAPER or SCISSORS.
            val opponentMove = moves[results[0]]
            // GameResult will be either LOSE, DRAW or WIN
            val gameResult = gameResults[results[1]]

            total += gameResultPoints[gameResult]!!
            total += calculateYourMovePoints(gameResult, opponentMove)
        }
        return total
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class Move {
    ROCK, PAPER, SCISSORS
}

enum class Result {
    LOSE, DRAW, WIN
}