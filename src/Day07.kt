class Node (val dir: String, var value: Long, val parent: Node?) {
    var children: MutableList<Node> = mutableListOf()

    fun updateDirValues(newValue: Long) {
        value += newValue

        if(this.parent != null) {
            this.parent.updateDirValues(newValue)
        }
    }

    fun addChild(dir: String) {
        children.add(Node(dir, 0, this))
    }

    fun getChild(dir: String): Node? {
        return this.children.find {
            it.dir == dir
        }
    }
}

operator fun List<Node>.contains(other: String): Boolean {
    this.map {
        if(it.dir == other) {
            return true
        }
    }
    return false
}

fun main() {
    val maxSize = 100000
    val totalSpace = 70000000
    val spaceNeeded = 30000000
    val rootNode = Node("/", 0, null)

    fun part1(input: List<String>): Long {
        var currentNode = rootNode
        fun handleCd(dir: String) {
            currentNode = when (dir) {
                "/" -> {
                    rootNode
                }

                ".." -> {
                    currentNode.parent!!
                }

                else -> {
                    if(!currentNode.children.contains(dir)) {
                        currentNode.addChild(dir)
                    }
                    currentNode.getChild(dir)!!
                }
            }
        }

        fun handleLine(line: List<String>) {
            if(line[1] == "cd") {
                handleCd(line[2])
            } else if(line[0][0].isDigit()) {
                currentNode.updateDirValues(line[0].toLong())
            }
        }

        fun calculateTotal(node: Node): Long {
            if(node.children.isEmpty()) {
                return if(node.value <= maxSize) node.value else 0
            }

            if(node.value <= maxSize) {
                return node.value + node.children.sumOf { calculateTotal(it) }
            }

            return node.children.sumOf { calculateTotal(it) }
        }

        input.map {handleLine(it.split(" ")) }
        return calculateTotal(rootNode)
    }

    fun part2(): Long {
        fun calculateSmallestRemoval(node: Node, removalSizeMin: Long): Long {
            if(node.value >= removalSizeMin) {
                return node.value.coerceAtMost(node.children.minOf { calculateSmallestRemoval(it, removalSizeMin) })
            }

            if(node.children.isEmpty()) {
                return Long.MAX_VALUE
            }

            return node.children.minOf { calculateSmallestRemoval(it, removalSizeMin) }
        }

        val currentTotal = totalSpace - rootNode.value
        if(currentTotal > spaceNeeded) {
            return 0
        }

        val removalSizeMin = spaceNeeded - currentTotal
        return calculateSmallestRemoval(rootNode, removalSizeMin)
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2())
}
