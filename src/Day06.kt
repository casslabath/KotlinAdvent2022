import java.lang.IllegalArgumentException

fun main() {
    fun indexOfNLengthUniqueSubstring(input: CharArray, n: Int): Int {
        var count = 0
        var sub = ""
        input.map { letter ->
            count++

            if (sub.contains(letter)) {
                // Shift substring so all characters are unique after
                // the next is added.
                while (sub[0] != letter) {
                    sub = sub.substring(1)
                }
                sub = sub.substring(1)
            }

            sub += letter
            if (sub.length == n) {
                return count
            }
        }
        throw IllegalArgumentException("Input does not contain a $n length unique substring")
    }

    fun part1(input: List<String>): Int {
        return  indexOfNLengthUniqueSubstring(input[0].toCharArray(), 4)
    }

    fun part2(input: List<String>): Int {
        return  indexOfNLengthUniqueSubstring(input[0].toCharArray(), 14)
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
