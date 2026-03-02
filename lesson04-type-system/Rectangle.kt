//package pt.isel


class Rectangle(val height: Int, var width: Int)
{
     val area
         get() = height * width
    //val area = height * width
//
    override fun toString() : String{
        return "$width, $height"
    }
}

fun printWidth(r: Rectangle) {
    println(r.width)
}


fun main() {
    var r = Rectangle(10, 20)
    println(r)
    var r1 = Rectangle(10, 20)
    println(r1)
    val r2 = r;
    println(r2.width)

    r.width = 15
    println(r2.width)

    println(r.equals(r1))
}