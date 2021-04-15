package pl.setblack.k.pure.p3

import io.vavr.collection.Seq

sealed class Order(internal val sum: Int) {
    open fun income(): Int = sum
    fun cancelled() = Naleśnik { Cancelled(this) }
}

class Created(s: Int) : Order(s) {
    override fun income(): Int = 0
    fun pay() = Naleśnik { Paid(this) }
}

class Paid internal constructor(order: Created) : Order(order.sum) {
    fun deliver(): Naleśnik<Delivered> = Naleśnik {
        Delivered(this).also {
            println("delivered")
        }
    }

    fun deliverFast(): Naleśnik<Delivered> = Naleśnik {
        Delivered(this).also {
            println("delivered fast")
        }
    }
}

class Delivered internal constructor(order: Paid) : Order(order.sum) {
    fun archive() = Archived(this)
}

class Cancelled internal constructor(order: Order) : Order(order.sum) {
    override fun income() = 0
}

class Archived internal constructor(order: Delivered) : Order(order.sum)

//val orders = list(
//    Created(120),
//    Created(20).pay(),
//    Created(30).pay().deliver(),
//    Created(40).pay(),
//    Created(20).pay().cancelled(),
//    Created(30).pay().deliver().archive()
//)

fun pureSum(orders: Seq<Order>): Int =
    orders.foldLeft(0) { accumulator, order ->
        accumulator + order.income()
    }


fun main() {
    crashDelivery().zjedz()
}
//    println(pureSum(orders))


fun crashDelivery() = run {
    val created = Created(25)
    val paid = created.pay()
    paid.zawiń { it.deliver() }
    paid.zawiń {  if (it.income()> 20) it.deliverFast()  else it.deliver() }
}


class Naleśnik<out A>(private val nadzienie: () -> A) {

    fun <B> przepakuj(f: (A) -> B) = Naleśnik { f(this.nadzienie()) }

    fun <B> zawiń(f: (A) -> Naleśnik<B>): Naleśnik<B> = Naleśnik { f(this.nadzienie()).nadzienie() }

    fun zjedz(): A = nadzienie()
}
