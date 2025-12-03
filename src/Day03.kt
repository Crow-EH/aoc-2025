import kotlin.math.pow

fun main() {
    fun parse(input: List<String>): List<List<Int>> {
        return input.map { it.map { c -> c.digitToInt() } }
    }

    fun part1(input: List<List<Int>>): Long {
        return input.sumOf { bank ->
            val maxExceptLast = bank.take(bank.size - 1).max()
            val firstDigitIdx = bank.indexOf(maxExceptLast)
            val maxAfterFirstDigit = bank.takeLast(bank.size - firstDigitIdx - 1).max()
            val joltage = maxExceptLast.toLong() * 10 + maxAfterFirstDigit
            println("$bank -> $maxExceptLast & $maxAfterFirstDigit -> $joltage")
            joltage
        }
    }

    fun part2(input: List<List<Int>>): Long {
        return input.sumOf { bank ->
            print("$bank -> ")
            val bankResult = (1..12).fold(Pair(0L, -1)) { (total, prevIdx), digit ->
                val bankAfterPrevWithSpace = bank.subList(prevIdx + 1, bank.size - 12 + digit)
                val max = bankAfterPrevWithSpace.max()
                val digitIdx = prevIdx + 1 + bankAfterPrevWithSpace.indexOf(max)
                val tenMultiplier = 10.toDouble().pow(12 - digit).toLong()
                print("$max ")
                Pair(total + max * tenMultiplier, digitIdx)
            }
            println("-> ${bankResult.first}")
            bankResult.first
        }

    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    check(part1(parse(testInput)) == 357.toLong())
    println("test part1 ok")
    part1(parse(input)).println()

    check(part2(parse(testInput)) == 3121910778619)
    println("test part2 ok")
    part2(parse(input)).println()
}
