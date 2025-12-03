import kotlin.math.pow

fun main() {
    fun parse(input: List<String>): List<Pair<String, String>> {
        return input[0].split(",").map { range ->
            val fromTo = range.split("-")
            Pair(fromTo[0], fromTo[1])
        }
    }

    fun getPossibleInvalidValues1(minLen: Int, maxLen: Int, min: Long, max: Long): LongArray {
        return (minLen..maxLen).fold(longArrayOf()) { values, len ->
            if (len % 2 != 0) {
                values
            } else {
                val digits = len / 2
                val tenMultBelowIncl = 10.toDouble().pow(digits - 1).toLong()
                val nearestTenMultAbove = 10.toDouble().pow(digits).toLong()
                values + (tenMultBelowIncl..<nearestTenMultAbove).fold(longArrayOf()) { acc, half ->
                    val candidate = half.toString().repeat(2).toLong()
                    if (candidate in min..max) {
                        acc + candidate
                    } else {
                        acc
                    }
                }
            }
        }
    }

    fun part1(input: List<Pair<String, String>>): Long {
        return input.fold(0) { acc, fromTo ->
            val from = fromTo.first
            val to = fromTo.second
            val invalidValues = getPossibleInvalidValues1(from.length, to.length, from.toLong(), to.toLong())
            println("from $from to $to")
            if (invalidValues.isEmpty()) {
                println("nothing")
                acc
            } else {
                invalidValues.forEach { println(it) }
                val invalidSum = invalidValues.sum()
                val newAcc = acc + invalidSum
                println("sum : $acc + $invalidSum = $newAcc")
                newAcc
            }
        }
    }

    fun getProperDivisors(num: Int): List<Int> {
        return (1..num / 2).filter { num % it == 0 }
    }

    fun getPossibleInvalidValues2(minLen: Int, maxLen: Int, min: Long, max: Long): List<Long> {
        return (minLen..maxLen).fold(longArrayOf()) { values, len ->
            if (len <= 1) {
                values
            } else {
                values + getProperDivisors(len).flatMap { digits ->
                    val repeat = len / digits
                    val tenMultBelowIncl = 10.toDouble().pow(digits - 1).toLong()
                    val nearestTenMultAbove = 10.toDouble().pow(digits).toLong()
                    (tenMultBelowIncl..<nearestTenMultAbove).fold(longArrayOf()) { acc, part ->
                        val candidate = part.toString().repeat(repeat).toLong()
                        if (candidate in min..max) {
                            acc + candidate
                        } else {
                            acc
                        }
                    }.toList()
                }
            }
        }.distinct()
    }

    fun part2(input: List<Pair<String, String>>): Long {
        return input.fold(0) { acc, fromTo ->
            val from = fromTo.first
            val to = fromTo.second
            val invalidValues = getPossibleInvalidValues2(from.length, to.length, from.toLong(), to.toLong())
            println("from $from to $to")
            if (invalidValues.isEmpty()) {
                println("nothing")
                acc
            } else {
                invalidValues.forEach { println(it) }
                val invalidSum = invalidValues.sum()
                val newAcc = acc + invalidSum
                println("sum : $acc + $invalidSum = $newAcc")
                newAcc
            }
        }
    }

    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    check(part1(parse(testInput)) == 1227775554.toLong())
    println("test part1 ok")
    part1(parse(input)).println()

    check(part2(parse(testInput)) == 4174379265)
    println("test part2 ok")
    part2(parse(input)).println()
}
