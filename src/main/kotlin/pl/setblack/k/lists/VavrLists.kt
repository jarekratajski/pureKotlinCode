package pl.setblack.k.lists

import io.vavr.kotlin.list

fun main() {
    run {
        val list1  = list(1, 2, 3)
        val list2 = list1.prepend(0)
        println(list1)
        println(list2)
    }
}
