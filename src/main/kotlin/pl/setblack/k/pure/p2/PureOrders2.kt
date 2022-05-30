package pl.setblack.k.pure.p2

import io.vavr.collection.Seq
import io.vavr.kotlin.list

sealed class Order(internal val sum: Int) {
    open fun income(): Int = sum
    fun cancelled() = Cancelled(this)
}

class Created(s: Int) : Order(s) {
    override fun income(): Int = 0
    fun pay() = Paid(this)
}

class Paid internal constructor(order: Created) : Order(order.sum) {
    fun deliver() = Delivered(this).also {
        println("delivered")
    }

    fun deliverFast() = Delivered(this).also {
        println("delivered fast")
    }
}

class Delivered internal constructor(order: Paid) : Order(order.sum) {
    fun archive() = Archived(this)
}

class Cancelled internal constructor(order: Order) : Order(order.sum) {
    override fun income() = 0
}

class Archived internal constructor(order: Delivered) : Order(order.sum)

fun pureSum(orders: Seq<Order>): Int =
    orders.foldLeft(0) { accumulator, order ->
        accumulator + order.income()
    }


fun main() = crashDelivery()
//    println(pureSum(orders))



fun crashDelivery() = run {
    val created = Created(25)
    val paid = created.pay()
    paid.deliver()
    if (paid.income() > 20) {
        paid.deliverFast()
    }
}
