import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/** Reads lines from the given input txt file. */
fun readInput(name: String) = Path("src/$name.txt").readText().lines().dropLast(1)

/** Converts string to md5 hash. */
fun String.md5() =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')

/** The cleaner shorthand for printing output. */
fun Any?.println() = println(this)

fun <T> List<List<T>>.transpose(cellFilter: (T) -> Boolean): List<List<T>> {
  return this[0].indices.map { x -> this.indices.map { y -> this[y][x] }.filter(cellFilter) }
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
  return transpose { true }
}

@JvmName("transposeCharSequences")
fun List<CharSequence>.transpose(cellFilter: (Char) -> Boolean): List<List<Char>> {
  return this[0].indices.map { x -> this.indices.map { y -> this[y][x] }.filter(cellFilter) }
}

@JvmName("transposeCharSequences")
fun List<CharSequence>.transpose(): List<List<Char>> {
  return transpose { true }
}
