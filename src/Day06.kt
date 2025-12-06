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
        .foldRight(Pair(mutableListOf<Long>(), 0L)) { column, (numbers, total) ->
          when {
            column.isEmpty() -> Pair(numbers, total)
            column.last() !in validOperators -> {
              val number = column.joinToString("").toLong()
              numbers.add(number)
              Pair(numbers, total)
            }
            else -> {
              val number = column.dropLast(1).joinToString("").toLong()
              numbers.add(number)
              Pair(
                  mutableListOf(),
                  when (column.last()) {
                    ADD -> total + numbers.sum()
                    MULTIPLY -> total + numbers.fold(1L) { acc, n -> acc * n }
                    else -> total
                  },
              )
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
