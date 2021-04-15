package pl.setblack.k.pure

import io.vavr.control.Option
import io.vavr.kotlin.Try
import io.vavr.kotlin.option


class IO<A>(private val action: () -> A) {

    fun <B> map(f: (A) -> B) = IO { f(action()) }

    fun <B> flatMap(f: (A) -> IO<B>): IO<B> = IO { f(action()).unsafeRun() }

    internal fun unsafeRun(): A = action()
}

fun readInt(): IO<Option<Int>> = IO {
    Try {
        readLine().option().map(String::toInt)
    }.toOption().flatMap { it }
}

fun prompt(): IO<Unit> = IO { print(">") }

fun loop(): IO<Unit> = run {
    prompt().flatMap {
        readInt().flatMap {
            it.map { v ->
                if (v > 1) {
                    loop()
                } else {
                    IO { Unit }
                }
            }.getOrElse { IO { Unit } }
        }
    }
}


fun main() = run {
    loop().unsafeRun()
}


