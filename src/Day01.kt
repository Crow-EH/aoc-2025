import kotlin.math.abs

fun main() {

  fun parse(input: String): Int {
    val direction = input.substring(0..<1)
    val clicks = input.substringAfter(direction).toInt()
    return if (direction == "L") {
      -clicks
    } else {
      clicks
    }
  }

  fun part1(input: List<String>): Int {
    val result =
        input
            .map { parse(it) }
            .fold(Pair(0, 50)) { (zs, pos), clicks ->
              val raw = pos + clicks
              val newPos = raw.mod(100)
              val newZs = if (newPos == 0) zs + 1 else zs
              Pair(newZs, newPos)
            }
    return result.first
  }

  fun part2(input: List<String>): Int {
    val result =
        input
            .map { parse(it) }
            .fold(Pair(0, 50)) { (zs, pos), clicks ->
              val rawPos = pos + clicks
              val endOnZ = if (rawPos <= 0 && clicks != rawPos) 1 else 0
              val passZ = abs(rawPos) / 100
              val newPos = rawPos.mod(100)
              val newZs = zs + endOnZ + passZ
              Pair(newZs, newPos)
            }
    return result.first
  }

  val testInput = readInput("Day01_test")
  check(part1(testInput) == 3)
  println("test part1 ok")
  check(part2(testInput) == 6)
  println("test part2 ok")

  val input = readInput("Day01")
  part1(input).println()
  part2(input).println()
}
