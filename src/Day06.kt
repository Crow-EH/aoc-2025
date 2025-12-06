const val ADD = '+'
const val MULTIPLY = '*'
val validOperators = arrayOf(ADD, MULTIPLY)

fun main() {
  fun part1(input: List<String>): Long {
    return input
        .map { line -> line.split(" ").filter { it != "" } }
        .transpose()
        .sumOf { problem ->
          when (problem[problem.size - 1]) {
            ADD.toString() -> problem.dropLast(1).sumOf { it.toLong() }
            MULTIPLY.toString() -> problem.dropLast(1).fold(1) { acc, n -> acc * n.toLong() }
            else -> error("YOU DARE LIE TO ME ?!")
          }
        }
  }

  fun part2(input: List<String>): Long {
    return input
        .transpose { it != ' ' }
        .foldRight(Pair(emptyList<Long>(), 0L)) { column, (numbers, total) ->
          when {
            column.isEmpty() -> Pair(numbers, total)
            column.last() !in validOperators -> {
              Pair(numbers + column.joinToString("").toLong(), total)
            }
            else -> {
              val finalNumbers = numbers + column.dropLast(1).joinToString("").toLong()
              val newTotal =
                  when (column.last()) {
                    ADD -> total + finalNumbers.sum()
                    MULTIPLY -> total + finalNumbers.fold(1L) { acc, n -> acc * n }
                    else -> total
                  }
              Pair(emptyList(), newTotal)
            }
          }
        }
        .second
  }

  val testInput = readInput("Day06_test")
  val input = readInput("Day06")

  check(part1(testInput) == 4277556L)
  println("test part1 ok")
  part1(input).println()

  check(part2(testInput) == 3263827L)
  println("test part2 ok")
  part2(input).println()
}
