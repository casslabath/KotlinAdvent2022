enum class Direction {UP, DOWN, LEFT, RIGHT}
fun main() {
    fun treeVisibleInDir(grid: List<String>, x: Int, y: Int, treeHeight: Int, direction: Direction): Pair<Boolean, Int> {
        when(direction) {
            Direction.UP -> {
                if(y-1 < 0) {
                    return true to 0
                }
                if(treeHeight <= grid[x][y-1].code) {
                    return false to 1
                }

                val recur = treeVisibleInDir(grid,x,y-1, treeHeight, direction)
                return recur.first to recur.second + 1
            }
            Direction.DOWN -> {
                if(y+1 == grid.size) {
                    return true to 0
                }
                if(treeHeight <= grid[x][y+1].code) {
                    return false to 1
                }
                val recur = treeVisibleInDir(grid,x,y+1, treeHeight, direction)
                return recur.first to recur.second + 1            }
            Direction.LEFT -> {
                if(x-1 < 0) {
                    return true to 0
                }
                if(treeHeight <= grid[x-1][y].code) {
                    return false to 1
                }
                val recur = treeVisibleInDir(grid,x-1,y, treeHeight, direction)
                return recur.first to recur.second + 1
            }
            Direction.RIGHT -> {
                if(x+1 == grid[0].length) {
                    return true to 0
                }
                if(treeHeight <= grid[x+1][y].code) {
                    return  false to 1
                }
                val recur = treeVisibleInDir(grid,x+1,y, treeHeight, direction)
                return recur.first to recur.second + 1            }
        }
    }

    fun Pair<Boolean, Int>.getVisibility(): Boolean {
        return this.first
    }

    fun Pair<Boolean, Int>.getVisibileTrees(): Int {
        return this.second
    }

    fun part1And2(input: List<String>): Pair<Int, Int> {
        val width = input[0].length
        val height = input.size
        var visibleTrees = 0
        var maxScenicScore = 0

        for(y in 0 until width) {
            for(x in 0 until height) {
                val treeHeight = input[x][y].code

                // a list of pairs representing if the tree is visible
                // and its number of visible trees
                val treeVisibilities: List<Pair<Boolean, Int>> = Direction.values().map {
                    treeVisibleInDir(input,x,y,treeHeight,it)
                }

                // check if tree is visible in any direction
                if(treeVisibilities.any{it.getVisibility()}) {
                    visibleTrees++
                }

                // update max scenic score
                maxScenicScore = maxScenicScore.coerceAtLeast(
                    // calculate the scenic score by multiplying each
                    // direction's visible trees
                    treeVisibilities.map { it.getVisibileTrees() }.reduce { acc, i ->  acc * i}
                )
            }
        }
        return visibleTrees to maxScenicScore
    }

    val input = readInput("Day08")
    println(part1And2(input))
}
