fun main() {
    fun part1(input: List<String>): Int {
        var cycleStop = 20
        var cycle = 0
        var x = 1
        val signalStrengths = mutableListOf<Int>()

        input.map{
            val splitStr = it.split(" ")
            val op = splitStr[0]
            if(op == "addx") {
                val num = splitStr[1].toInt()

                for(i in 0..1) {
                    cycle++
                    // if the cycle is at 20,60,100,140,180 or 220 get signal strength
                    if(cycle == cycleStop) {
                        signalStrengths.add(x * cycle)
                        cycleStop += 40
                    }
                    // if this is the second cycle for the addx, add num to x
                    if(i == 1) {
                        x += num
                    }
                }
            } else {
                cycle++
                // if the cycle is at 20,60,100,140,180 or 220 get signal strength
                if(cycle == cycleStop) {
                    signalStrengths.add(x * cycle)
                    cycleStop += 40
                }
            }
        }
        return signalStrengths.sum()
    }

    fun part2(input: List<String>) {
        val pixels = MutableList(240){'.'}
        var xPos = mutableListOf(0,1,2)
        var cycle = 0
        input.map{
            val splitStr = it.split(" ")
            val op = splitStr[0]
            if(op == "addx") {
                val num = splitStr[1].toInt()

                for(i in 0..1) {
                    // draw pixel if cycle location is in the register
                    if(xPos.contains((cycle).mod(40))) {
                        pixels[cycle] = '#'
                    }
                    cycle++
                    // On the second cycle of the addx set the register at the num
                    if(i == 1) {
                        val x = xPos[1] + num
                        xPos = mutableListOf(x-1,x,x+1)
                    }
                }
            } else {
                // draw pixel if cycle location is in the register
                if(xPos.contains((cycle).mod(40))) {
                    pixels[cycle] = '#'
                }
                cycle++
            }
        }
        pixels.chunked(40) {
            it.forEach{num -> print("$num ") }
            println()
        }
    }

    val input = readInput("Day10")
    println(part1(input))
    part2(input)
}
