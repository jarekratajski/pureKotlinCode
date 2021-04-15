package pl.setblack.k.pure

import io.vavr.collection.Seq
import io.vavr.kotlin.list


sealed class Order(val sum: Int) {

    class Created(s: Int) : Order(s) {
        fun pay(): IO<Paid> = IO { Paid(this) }
    }

    class Paid internal constructor(created: Created) : Order(created.sum) {
        fun deliver() = IO {
            Delivered(this).also {
                println("Deliver")
            }
        }

        fun deliverFast() = IO {
            Delivered(this).also {
                println("Deliver fast")
            }
        }
    }

    class Delivered internal constructor(paid: Paid) : Order(paid.sum)

}

fun main() = run {
    val order = Order.Created(7)

    val paid = order.pay()

    val delivered = paid.flatMap { p ->
        if (p.sum > 100) {
            p.deliverFast()
        }
        p.deliver()
    }
    val a = 8
    if (a > 5) {
        println("ok")
    }


    delivered.unsafeRun()

    Unit
}


//fun impureSum(orders: Seq<Order>) {
//
//}
