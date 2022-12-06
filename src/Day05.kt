fun main() {
    fun part1(input: List<String>): String {
        val stacks = mutableListOf(
            mutableListOf('R','S','L','F','Q'),
            mutableListOf('N','Z','Q','G','P','T'),
            mutableListOf('S','M','Q','B'),
            mutableListOf('T','G','Z','J','H','C','B','Q'),
            mutableListOf('P','H','M','B','N','F','S'),
            mutableListOf('P','C','Q','N','S','L','V','M'),
            mutableListOf('W','C','F'),
            mutableListOf('Q','H','G','Z','W','V','P','M'),
            mutableListOf('G','Z','D','L','C','N','R')
        )

        input.map {line ->
            val (number, fromLoc, toLoc) = line
                .replace("move ", "")
                .replace("from ", "")
                .replace("to ", "")
                .split(" ")
                .map { it.toInt() }

            for(i in 1..number) {
                stacks[toLoc-1].add(stacks[fromLoc-1].removeLast())
            }
        }

        var top = ""
        stacks.map { top += it.last() }
        return top
    }

    fun part2(input: List<String>): String {
        val stacks = mutableListOf(
            mutableListOf('R','S','L','F','Q'),
            mutableListOf('N','Z','Q','G','P','T'),
            mutableListOf('S','M','Q','B'),
            mutableListOf('T','G','Z','J','H','C','B','Q'),
            mutableListOf('P','H','M','B','N','F','S'),
            mutableListOf('P','C','Q','N','S','L','V','M'),
            mutableListOf('W','C','F'),
            mutableListOf('Q','H','G','Z','W','V','P','M'),
            mutableListOf('G','Z','D','L','C','N','R')
        )

        input.map {line ->
            val (number, fromLoc, toLoc) = line
                .replace("move ", "")
                .replace("from ", "")
                .replace("to ", "")
                .split(" ")
                .map { it.toInt() }

            stacks[fromLoc-1].takeLast(number).map { stacks[toLoc-1].add(it) }

            for(i in 1..number) {
                stacks[fromLoc-1].removeLast()
            }
        }

        var top = ""
        stacks.map { top += it.last() }
        return top
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
