package pt.isel

import java.net.URI
import java.time.LocalDate
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty
import kotlin.reflect.KVisibility
import kotlin.reflect.KVisibility.*
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.primaryConstructor


data class A(val x: Int = 0, val y: Int = 0)  {
//    constructor(x: Int) : this(x,0) {}
//    constructor() : this(0,0) { }


    private fun f1(a: Int, s: String) : String{
        return "f1"
    }

    fun f2(a: Int): String {
        return "f2"
    }

    private fun f3(): String {
        return "f3"
    }
}

fun  A.f4(x: Int) { }

//fun  f4(a: A, x: Int) { }


class A1(val a: Int = 0, val b: Int = 0) {

}

fun checkMembers(obj: Any) {
    var kcls: KClass<*>  = obj::class;

    println("### " + kcls.simpleName)
    kcls
        .members
        .forEach { member ->
            println(
                (if (member is KProperty<*>) "Prop " else "Func ")
                        + member.name
            )
        }
}

fun checkAndCallMethods(obj: Any) {
    println("### " + obj::class.simpleName)
    obj::class
        .declaredMemberFunctions
        .filter { func ->
            func.parameters.size == 1 &&
                    func.parameters[0].kind == KParameter.Kind.INSTANCE &&
                    func.visibility == PUBLIC
        }.forEach { func ->
            println("Func ${func.name}(): ${func.returnType} ======> ${func.call(obj)}")
        }
}

fun main() {
    //checkMembers(URI("https://github.com/a/b?x=2#3"));
    checkAndCallMethods(URI("https://github.com/a/b?x=2#3"))
    checkAndCallMethods(LocalDate.now())
    checkAndCallMethods(A())

    var a = A(2,3)
    var a1 = A1(a.x, a.y)
}
