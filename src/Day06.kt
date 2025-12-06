fun main() {
  fun part1(input: List<String>): Long {
    return input
        .fold(mutableListOf<MutableList<String>>()) { acc, line ->
          line
              .split(" ")
              .filter { it != "" }
              .forEachIndexed { index, string ->
                if (acc.size == index) {
                  acc.add(mutableListOf())
                }
                acc[index].add(string)
              }
          acc
        }
        .sumOf { problem ->
          when (problem[problem.size - 1]) {
            "+" -> problem.dropLast(1).sumOf { it.toLong() }
            "*" -> problem.dropLast(1).fold(1) { acc, n -> acc * n.toLong() }
            else -> error("YOU DARE LIE TO ME ?!")
          }
        }
  }

  data class Problem(val numbers: MutableList<Long>, val operator: Char)
  fun part2(input: List<String>): Long {
    return input
        .fold(mutableListOf<MutableList<Char>>()) { acc, line ->
          line
              .map { it }
              .forEachIndexed { index, char ->
                if (acc.size == index) {
                  acc.add(mutableListOf())
                }
                if (char != ' ') acc[index].add(char)
              }
          acc
        }
        .fold(mutableListOf<Problem>()) { problems, column ->
          when {
            column.isEmpty() -> problems
            else -> {
              val (currentProblem, digits) =
                  when (column.last()) {
                    '+',
                    '*' -> {
                      val newProblem = Problem(mutableListOf(), column.last())
                      problems.add(newProblem)
                      Pair(newProblem, column.dropLast(1))
                    }
                    else -> Pair(problems.last(), column)
                  }
              val number = digits.joinToString("").toLong()
              currentProblem.numbers.add(number)
              problems
            }
          }
        }
        .sumOf { problem ->
          when (problem.operator) {
            '+' -> problem.numbers.sum()
            '*' -> problem.numbers.fold(1) { acc, n -> acc * n }
            else -> error("YOU DARE LIE TO ME ?!")
          }
        }
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
