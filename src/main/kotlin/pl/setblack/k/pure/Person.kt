package pl.setblack.k.pure

data class Person(val name:String, val age:Int, val drinking:Boolean = true)

@Suppress("ReturnUnit")
fun main() = run {
    val p = Person("irek", 27, true)
    val p1 = p.copy(name = "irek")
    println(p1)
    val p2  = p1.copy(age=28)
    val p3 = p2.copy(drinking = false)
    val p4 = p3.copy(age=29, drinking = true)
    val p5 = p4.copy(name="Zdzich", age= p4.age+1, drinking = false)
    println(p5)
}
