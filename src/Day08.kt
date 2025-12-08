import kotlin.math.pow

fun main() {

  data class Point3D(val x: Long, val y: Long, val z: Long) {
    fun squaredDistance(other: Point3D): Double =
        (x - other.x).toDouble().pow(2) +
            (y - other.y).toDouble().pow(2) +
            (z - other.z).toDouble().pow(2)
  }

  fun MutableList<MutableSet<Point3D>>.connect(a: Point3D, b: Point3D) {
    val circuitA = removeFirst { a in it } ?: mutableSetOf(a, b)
    val circuitB = removeFirst { b in it } ?: emptySet()
    circuitA.add(b)
    circuitA.addAll(circuitB)
    add(circuitA)
  }

  fun parse(input: List<String>): List<Point3D> {
    return input
        .map { it.split(",") }
        .map { (a, b, c) -> Point3D(a.toLong(), b.toLong(), c.toLong()) }
  }

  fun part1(input: List<Point3D>, connections: Int): Long {
    val circuits = mutableListOf<MutableSet<Point3D>>()
    input
        .asSequence()
        .flatMapIndexed { i, a -> input.asSequence().drop(i + 1).map { b -> a to b } }
        .sortedBy { (a, b) -> a.squaredDistance(b) }
        .take(connections)
        .forEach { (a, b) -> circuits.connect(a, b) }
    return circuits
        .map { it.size.toLong() }
        .sortedDescending()
        .take(3)
        .reduce { acc, item -> acc * item }
  }

  fun part2(input: List<Point3D>): Long {
    val circuits = input.mapTo(mutableListOf()) { mutableSetOf(it) }
    val pairs =
        input
            .asSequence()
            .flatMapIndexed { i, a -> input.asSequence().drop(i + 1).map { b -> a to b } }
            .sortedBy { (a, b) -> a.squaredDistance(b) }
            .iterator()
    lateinit var last: Pair<Point3D, Point3D>
    while (circuits.size > 1) {
      last = pairs.next()
      circuits.connect(last.first, last.second)
    }
    return last.first.x * last.second.x
  }

  val testInput = readInput("Day08_test")
  val input = readInput("Day08")

  check(part1(parse(testInput), 10).peekPrint() == 40L)
  println("test part1 ok")
  part1(parse(input), 1000).println()

  check(part2(parse(testInput)) == 25272L)
  println("test part2 ok")
  part2(parse(input)).println()
}
