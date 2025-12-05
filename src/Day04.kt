fun main() {
    fun parse(input: List<String>): List<List<Int>> {
        return input.map { it.map { c -> if (c == '@') 1 else 0 } }
    }

    fun getAdjCells(y: Int, x: Int): List<Pair<Int, Int>> {
        return listOf(
            Pair(y - 1, x - 1),
            Pair(y - 1, x),
            Pair(y - 1, x + 1),
            Pair(y, x - 1),
            Pair(y, x + 1),
            Pair(y + 1, x - 1),
            Pair(y + 1, x),
            Pair(y + 1, x + 1),
        )
    }

    val emptyLine = emptyList<Int>()

    fun part1(input: List<List<Int>>): Int {
        return input.mapIndexed { y, line ->
            line.mapIndexed { x, cell ->
                if (cell == 1) {
                    val count =
                        getAdjCells(y, x).sumOf { (adjY, adjX) ->
                            input.getOrElse(adjY) { emptyLine }
                                .getOrElse(adjX) { 0 }
                        }
                    if (count < 4) 1 else 0
                } else {
                    0
                }
            }.sum()
        }.sum()
    }

    fun removeCells(grid: List<List<Int>>): Pair<List<List<Int>>, Int> {
        var removed = 0
        val newGrid = grid.mapIndexed { y, line ->
            line.mapIndexed { x, cell ->
                if (cell == 1) {
                    val count =
                        getAdjCells(y, x).sumOf { (adjY, adjX) ->
                            grid.getOrElse(adjY) { emptyLine }
                                .getOrElse(adjX) { 0 }
                        }
                    if (count < 4) {
                        removed++
                        0
                    } else {
                        1
                    }
                } else {
                    0
                }
            }
        }
        return Pair(newGrid, removed)
    }

    fun part2(input: List<List<Int>>): Int {
        var total = 0
        var currentGrid = input
        do {
            val (newGrid, removed) = removeCells(currentGrid)
            currentGrid = newGrid
            total += removed
        } while (removed != 0)
        return total
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    check(part1(parse(testInput)) == 13)
    println("test part1 ok")
    part1(parse(input)).println()

    check(part2(parse(testInput)) == 43)
    println("test part2 ok")
    part2(parse(input)).println()
}
