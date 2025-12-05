fun main() {
    fun parse(input: List<String>): Pair<List<LongRange>, List<Long>> {
        val ranges = input.takeWhile { it != "" }
            .map { it.split("-").run { first().toLong()..last().toLong() } }
        return Pair(ranges, input.takeLast(input.size - ranges.size - 1).map { it.toLong() })
    }

    fun part1(input: Pair<List<LongRange>, List<Long>>): Int {
        val ranges = input.first
        val ids = input.second
        return ids.filter { id -> ranges.any { it.contains(id) } }.size
    }

    data class Acc(val count: Long, val last: Long)

    fun part2(input: Pair<List<LongRange>, List<Long>>): Long {
        return input.first.sortedBy { it.first }
            .fold(Acc(0, 0)) { acc, range ->
                when {
                    acc.last >= range.last -> acc
                    acc.last >= range.first -> Acc(acc.count + range.last - acc.last, range.last)
                    else -> Acc(acc.count + range.last - range.first + 1, range.last)
                }
            }.count
    }

    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    check(part1(parse(testInput)) == 3)
    println("test part1 ok")
    part1(parse(input)).println()

    check(part2(parse(testInput)) == 14L)
    println("test part2 ok")
    part2(parse(input)).println()
}
