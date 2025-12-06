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

/** Shorthand that print then pass the value back */
fun <T> T.peekPrint() = println(this).let { this }

/** Transpose matrix (y of x -> x of y) and filter cells */
fun <T> List<List<T>>.transpose(cellFilter: (T) -> Boolean) =
    this[0].indices.map { x -> this.indices.map { y -> this[y][x] }.filter(cellFilter) }

/** Transpose matrix (y of x -> x of y) */
fun <T> List<List<T>>.transpose() = transpose { true }

/** Transpose matrix (y of x -> x of y) and filter cells */
@JvmName("transposeCharSequences")
fun List<CharSequence>.transpose(cellFilter: (Char) -> Boolean) =
    this[0].indices.map { x -> this.indices.map { y -> this[y][x] }.filter(cellFilter) }

/** Transpose matrix (y of x -> x of y) */
@JvmName("transposeCharSequences")
fun List<CharSequence>.transpose(): List<List<Char>> = transpose { true }
