fun main() {
    fun treeVisibleUp(grid: List<String>, x: Int, y: Int, treeHeight: Int): Boolean {
        if(y-1 < 0) {
            return true
        }
        if(treeHeight <= grid[x][y-1].code) {
            return false
        }
        return treeVisibleUp(grid,x,y-1, treeHeight)
    }

    fun treeVisibleDown(grid: List<String>, x: Int, y: Int, treeHeight: Int): Boolean {
        if(y+1 == grid.size) {
            return true
        }
        if(treeHeight <= grid[x][y+1].code) {
            return false
        }
        return treeVisibleDown(grid,x,y+1, treeHeight)
    }

    fun treeVisibleLeft(grid: List<String>, x: Int, y: Int, treeHeight: Int): Boolean {
        if(x-1 < 0) {
            return true
        }
        if(treeHeight <= grid[x-1][y].code) {
            return false
        }
        return treeVisibleLeft(grid,x-1,y,treeHeight)
    }

    fun treeVisibleRight(grid: List<String>, x: Int, y: Int, treeHeight: Int): Boolean {
        if(x+1 == grid[0].length) {
            return true
        }
        if(treeHeight <= grid[x+1][y].code) {
            return false
        }
        return treeVisibleRight(grid,x+1,y,treeHeight)
    }

    fun treeVisible(grid: List<String>, x: Int, y: Int): Boolean {
        val treeHeight = grid[x][y].code
        if(treeVisibleUp(grid,x,y,treeHeight) ||
            treeVisibleDown(grid,x,y,treeHeight) ||
            treeVisibleLeft(grid,x,y,treeHeight) ||
            treeVisibleRight(grid,x,y,treeHeight)) {
            return true
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val width = input[0].length
        val height = input.size
        var visibleTrees = 0
        for(y in 0 until width) {
            for(x in 0 until height) {
                if(treeVisible(input, x, y)) {
                    visibleTrees++
                }
            }
        }
        return visibleTrees
    }

    fun treeCountUp(grid: List<String>, x: Int, y: Int, treeHeight: Int): Int {
        if(y-1 < 0) {
            return 0
        }
        if(treeHeight <= grid[x][y-1].code) {
            return 1
        }
        return 1 + treeCountUp(grid,x,y-1, treeHeight)
    }

    fun treeCountDown(grid: List<String>, x: Int, y: Int, treeHeight: Int): Int {
        if(y+1 == grid.size) {
            return 0
        }
        if(treeHeight <= grid[x][y+1].code) {
            return 1
        }
        return 1 + treeCountDown(grid,x,y+1, treeHeight)
    }

    fun treeCountLeft(grid: List<String>, x: Int, y: Int, treeHeight: Int): Int {
        if(x-1 < 0) {
            return 0
        }
        if(treeHeight <= grid[x-1][y].code) {
            return 1
        }
        return 1 + treeCountLeft(grid,x-1,y,treeHeight)
    }

    fun treeCountRight(grid: List<String>, x: Int, y: Int, treeHeight: Int): Int {
        if(x+1 == grid[0].length) {
            return 0
        }
        if(treeHeight <= grid[x+1][y].code) {
            return 1
        }
        return 1 + treeCountRight(grid,x+1,y,treeHeight)
    }


    fun part2(input: List<String>): Int {
        var maxCount = 0
        val width = input[0].length
        val height = input.size

        for(y in 0 until width) {
            for(x in 0 until height) {
                val treeHeight = input[x][y].code

                if(treeVisible(input, x, y)) {
                    maxCount = maxCount.coerceAtLeast(
                        treeCountUp(input, x, y, treeHeight) *
                                treeCountDown(input, x, y, treeHeight) *
                                treeCountLeft(input, x, y, treeHeight) *
                                treeCountRight(input, x, y, treeHeight)
                    )
                }
            }
        }
        return maxCount
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
