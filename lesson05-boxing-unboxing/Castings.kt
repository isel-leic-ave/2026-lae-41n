open class A() {
}

open class B : A() {
}

class C : B() {
}

class D : A()

fun foo(a: A) {
    val o: Any = a
    val b: B = a as B // bytecode checkcast
}


fun bar() {
    var i: Int = Int.MAX_VALUE
    var l: Long = i.toLong()

    var l1: Long = (Int.MAX_VALUE+1L)
    var i1: Int = l1.toInt()

    println(i)
    println(l)
    println(l1)
    println(i1)
}

fun main() {
    bar()
//    var x = 2
//    var y = 3
//    //var z = x + y + y
//    var z = f(x, y)
//
//    var c: C = C()
//    var b: B = c
//    var a: A = b
//
//    println("Test an object B")
//    foo(B())
//    readLine()
//    println("Test an object A")
//    foo(D())
}


fun f(x : Int, y: Int): Int {
    return x + y
}