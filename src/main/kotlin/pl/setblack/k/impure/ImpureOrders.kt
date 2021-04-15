package pl.setblack.k.impure

import io.vavr.collection.Seq
import io.vavr.kotlin.list

sealed class Order(val sum: Int)

class Created(s: Int) : Order(s)

class Paid(s: Int) : Order(s)

class Delivered(s: Int) : Order(s)

class Cancelled(s:Int) : Order(s)

class Archived(s:Int) :Order(s)

val orders = list(
    Created(120),
    Paid(20),
    Delivered(30),
    Paid(40),
    Cancelled(20),
    Archived(10)
)

fun impureSum(orders: Seq<Order>): Int {
    var sum = 0
    for (order in orders) {
        when (order) {
            is Paid -> sum = sum + order.sum
            is Delivered -> sum = sum + order.sum
        }
    }
    return sum
}

fun main() {
    println(impureSum(orders))
}
