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

fun main() {
    var x = 2
    var y = 3
    //var z = x + y + y
    var z = f(x, y)

    println("Test an object B")
    foo(B())
    readLine()
    println("Test an object A")
    foo(D())
}


fun f(x : Int, y: Int): Int {
    return x + y
}