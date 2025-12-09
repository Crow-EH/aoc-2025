import java.awt.geom.Area
import java.awt.geom.Path2D
import kotlin.math.max
import kotlin.math.min

fun main() {

  data class Point(val x: Long, val y: Long)
  data class Corner(val position: Point, val biggestAreaWithSeen: Pair<Point, Long>)

  fun Point.areaWith(other: Point): Long =
      (max(this.x, other.x) - min(this.x, other.x) + 1) *
          (max(this.y, other.y) - min(this.y, other.y) + 1)

  fun Point.validAreaWith(area: Area, other: Point): Boolean {
    val topLeftX = min(x, other.x)
    val topLeftY = min(y, other.y)
    val botRightX = max(x, other.x)
    val botRightY = max(y, other.y)
    return area.contains(
        topLeftX.toDouble(),
        topLeftY.toDouble(),
        (botRightX - topLeftX).toDouble(),
        (botRightY - topLeftY).toDouble(),
    )
  }

  fun parse(input: List<String>): Pair<List<Point>, Area> {
    val initialPath = Path2D.Float()
    val points =
        input
            .map { line -> line.split(",") }
            .foldIndexed(Pair(emptyList<Point>(), initialPath)) { index, (points, path), (xs, ys) ->
              val x = xs.toLong()
              val y = ys.toLong()
              if (index == 0) {
                path.moveTo(x.toDouble(), y.toDouble())
              } else {
                path.lineTo(x.toDouble(), y.toDouble())
              }
              Pair(points + Point(x, y), path)
            }
    return Pair(points.first, Area(points.second))
  }

  fun part1(input: Pair<List<Point>, Area>): Long {
    return input.first
        .fold(Pair(emptyList<Corner>(), 0L)) { (corners, biggestAreaSeen), point ->
          if (corners.isEmpty()) {
            Pair(listOf(Corner(point, Pair(point, 0))), 0)
          } else {
            val seenPointWithMaxArea =
                corners.map { Pair(it.position, it.position.areaWith(point)) }.maxBy { it.second }
            Pair(
                corners +
                    Corner(
                        point,
                        Pair(seenPointWithMaxArea.first, seenPointWithMaxArea.second),
                    ),
                if (seenPointWithMaxArea.second > biggestAreaSeen) seenPointWithMaxArea.second
                else biggestAreaSeen,
            )
          }
        }
        .second
  }

  fun part2(input: Pair<List<Point>, Area>): Long {
    return input.first
        .fold(Pair(emptyList<Corner>(), 0L)) { (corners, biggestAreaSeen), point ->
          if (corners.isEmpty()) {
            Pair(listOf(Corner(point, Pair(point, 0))), 0)
          } else {
            val seenPointWithMaxArea =
                corners
                    .map {
                      if (point.validAreaWith(input.second, it.position)) {
                        Pair(it.position, it.position.areaWith(point))
                      } else Pair(it.position, 0L)
                    }
                    .maxBy { it.second }
            Pair(
                corners +
                    Corner(
                        point,
                        seenPointWithMaxArea,
                    ),
                if (seenPointWithMaxArea.second > biggestAreaSeen) seenPointWithMaxArea.second
                else biggestAreaSeen,
            )
          }
        }
        .second
  }

  val testInput = readInput("Day09_test")
  val input = readInput("Day09")

  check(part1(parse(testInput)) == 50L)
  println("test part1 ok")
  part1(parse(input)).println()

  check(part2(parse(testInput)) == 24L)
  println("test part2 ok")
  part2(parse(input)).println()
}
