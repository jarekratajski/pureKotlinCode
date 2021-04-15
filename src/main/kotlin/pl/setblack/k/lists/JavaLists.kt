package pl.setblack.k.lists

fun main() {
    run {
        val list1  = ArrayList(listOf(1,2,3))
        for (el in  list1 ) {
            list1.add(list1.size)
        }
        println(list1)
    }
}
