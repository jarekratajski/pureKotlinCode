package pl.setblack.k.lists

import io.vavr.kotlin.list
import java.util.Arrays

fun main() {
    run {
        val list1  = listOf(1, 2, 3)
        val list2 = listOf(0)+ list1
        println(list1)
        println(list2)
    }

    run {
        val list1  =   listOf(1, 2, 3)
        val list2 = listOf(0)+ list1
        (list1 as MutableList)[1] = -1
        println(list1)
        println(list2)
    }
}
