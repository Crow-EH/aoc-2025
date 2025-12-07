typealias Point = Pair<Int, Int>

fun main() {
  data class Splitter(val position: Point, val paths: Long)

  data class BeamPlinko(
      val splitters: MutableMap<Point, Splitter>,
      val unblockedBeams: MutableMap<Int, List<Point>>,
  )

  fun parse(input: List<String>): BeamPlinko {
    return input.foldIndexed(
        BeamPlinko(splitters = mutableMapOf(), unblockedBeams = mutableMapOf())
    ) { lineIndex, plinko, line ->
      line.forEachIndexed { columnIndex, cell ->
        when (cell) {
          'S' -> {
            plinko.unblockedBeams[columnIndex] = listOf(Point(lineIndex, columnIndex))
          }
          '^' -> {
            val beamOrigins = plinko.unblockedBeams[columnIndex]
            if (beamOrigins != null) {
              plinko.unblockedBeams.remove(columnIndex)
              val splitterPosition = Point(lineIndex, columnIndex)

              val leftCol = columnIndex - 1
              val leftBeamOrigins = plinko.unblockedBeams.getOrElse(leftCol) { emptyList() }
              plinko.unblockedBeams[leftCol] = leftBeamOrigins + splitterPosition

              val rightCol = columnIndex + 1
              val rightBeamOrigins = plinko.unblockedBeams.getOrElse(rightCol) { emptyList() }
              plinko.unblockedBeams[rightCol] = rightBeamOrigins + splitterPosition

              val paths = beamOrigins.sumOf { origin -> plinko.splitters[origin]?.paths ?: 1 }
              plinko.splitters[splitterPosition] = Splitter(splitterPosition, paths)
            }
          }
        }
      }
      plinko
    }
  }

  fun part1(input: BeamPlinko): Int {
    return input.splitters.size
  }

  fun part2(input: BeamPlinko): Long {
    return input.unblockedBeams.values.sumOf { origins ->
      origins.sumOf { input.splitters[it]?.paths ?: 0 }
    }
  }

  val testInput = readInput("Day07_test")
  val input = readInput("Day07")

  check(part1(parse(testInput).peekPrint()) == 21)
  println("test part1 ok")
  part1(parse(input)).println()

  check(part2(parse(testInput)) == 40L)
  println("test part2 ok")
  part2(parse(input)).println()
}
