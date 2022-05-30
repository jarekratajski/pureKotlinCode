package pl.setblack.k.pure.p1

import io.vavr.collection.Seq
import io.vavr.kotlin.list

sealed class Order(val sum: Int)

class Created(s: Int) : Order(s)

class Paid(s: Int) : Order(s)

class Delivered(s: Int) : Order(s)

//class Cancelled(s:Int) : Order(s)
//
//class Archived(s:Int) :Order(s)

val orders = list(
    Created(120),
    Paid(20),
    Delivered(30),
    Paid(40),
//    Cancelled(20),
//    Archived(10)
)

fun pureSum(orders: Seq<Order>): Int =
    orders.foldLeft(0) { accumulator, order ->
        when (order) {
            is Paid -> accumulator + order.sum
            is Delivered -> accumulator + order.sum
            is Created -> accumulator
        }
    }


fun main() {
    println(pureSum(orders))
}
