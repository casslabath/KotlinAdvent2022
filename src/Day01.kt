fun main() {
    fun part1(input: List<String>): Int {
        var most = Int.MIN_VALUE;
        var currentCals = 0;
        for(cal in input) {
            if(cal.isEmpty()) {
                most = currentCals.coerceAtLeast(most);
                currentCals = 0;
            } else {
                currentCals += Integer.parseInt(cal);
            }
        }
        return most;
    }

    fun part2(input: List<String>): Int {
        var topThree = mutableListOf(0,0,0);
        var currentCals = 0;

        for(cal in input) {
            if(cal.isEmpty()) {
                if(currentCals > topThree[0]) {
                    topThree[0] = currentCals;
                    topThree.sort();
                }
                currentCals = 0;
            } else {
                currentCals += Integer.parseInt(cal);
            }
        }
        return topThree.sum();
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
