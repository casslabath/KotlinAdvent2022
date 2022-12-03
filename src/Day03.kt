import java.util.*

fun main() {
    fun initPriorityMap(): Map<Char, Int> {
        val lower = "abcdefghijklmnopqrstuvwxyz"
        val upper = lower.uppercase(Locale.getDefault())
        val map: MutableMap<Char, Int> = mutableMapOf()

        var count = 1
        for(letter in lower.toCharArray()) {
            map[letter] = count
            count++
        }

        for(letter in upper.toCharArray()) {
            map[letter] = count
            count++
        }

        return map
    }

    fun part1(input: List<String>): Int {
        val priorityMap = initPriorityMap()
        var total = 0

        for(sack in input) {
            val componentOne = sack.substring(0, sack.length / 2).toCharArray()
            val componentTwo = sack.substring(sack.length / 2).toCharArray()

            for(letter in componentOne) {
                if(componentTwo.contains(letter)) {
                    total += priorityMap[letter]!!
                    break
                }
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val priorityMap = initPriorityMap()
        var total = 0

        for(i in input.indices step 3) {
            val sackOne: Set<Char> = input[i].toCharArray().toSet()
            val sackTwo = input[i+1].toCharArray().toSet()
            val sackThree = input[i+2].toCharArray().toSet()

            for(letter in sackOne) {
                if(sackTwo.contains(letter) && sackThree.contains(letter)) {
                    total += priorityMap[letter]!!
                    break
                }
            }
        }
        return total
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
