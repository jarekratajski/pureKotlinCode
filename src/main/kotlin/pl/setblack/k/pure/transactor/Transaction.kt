package pl.setblack.k.pure.transactor

import io.vavr.control.Either
import java.sql.Connection

class Transaction<A>(private val action: (Connection) -> A) {

    fun <B> map(f: (A) -> B) = Transaction { conn -> f(action(conn)) }

    fun <B> flatMap(f: (A) -> Transaction<B>) = Transaction { conn -> f(action(conn)).action(conn) }

    fun runTransaction(conn: Connection): Either<Exception, A> = run {
        val initialAC = conn.autoCommit
        conn.autoCommit = false
        try {
            val res = action(conn)
            conn.commit()
            Either.right(res)
        } catch (e: Exception) {
            conn.rollback()
            Either.left(e)
        } finally {
            conn.autoCommit = initialAC
        }
    }
}
