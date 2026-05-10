package pt.isel

import java.io.File
import java.lang.classfile.ClassFile
import java.lang.classfile.ClassFile.*
import java.lang.classfile.Interfaces.ofSymbols
import java.lang.constant.ClassDesc
import java.lang.constant.ConstantDescs.*
import java.lang.constant.MethodTypeDesc
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/*
 public final class pt.isel.Bar {
  public pt.isel.Bar();
    Code:
       0: aload_0
       1: invokespecial #8                  // Method java/lang/Object."<init>":()V
       4: return

  public final int foo();
    Code:
       0: ldc           #13                 // int 67895
       2: ireturn
 */

class MyBar(): IFoo {
    override fun foo(): Int {
        return 67896
    }
}

interface IFoo {
    fun foo(): Int
}

fun buildClassBar() {
    val className = "pt.isel.Bar"
    // val ifooDesc = ClassDesc("pt.isel.IFoo")
    val ifooDesc = ClassDesc.of(IFoo::class.qualifiedName)
    val bytes: ByteArray =
        ClassFile.of().build(ClassDesc.of(className)) { clb ->
            clb
                .withFlags(ACC_PUBLIC)
                .withInterfaces(ofSymbols(ifooDesc).interfaces())
                // .withMethod(INIT_NAME, MethodTypeDesc.of(CD_Void)) { mb: MethodBuilder ->
                .withMethod(INIT_NAME, MTD_void, ACC_PUBLIC) { mb ->
                    mb.withCode { cob ->
                        cob
                            .aload(0)
                            .invokespecial(CD_Object, INIT_NAME, MTD_void)
                            .return_()
                    }
                }.withMethod("foo", MethodTypeDesc.of(CD_int), ACC_PUBLIC) { mb ->
                    mb.withCode { cob ->
                        cob
                            .ldc(Integer.valueOf(67895) as Integer)
                            //.ldc(clb.constantPool().intEntry(67895))
                            .ireturn()
                    }
                }
        }
    val resourcePath =
        Unit::class.java
            .getResource("/")
            ?.toURI()
            ?.path


    File(resourcePath, className.replace('.', '/') + ".class")
        .also { println(it.absolutePath) }
        .also { it.parentFile.mkdirs() } // Create directories if they do not exist
        .writeBytes(bytes)
}

fun main() {
    buildClassBar()
    val barKlass: KClass<out Any> =
        Unit::class.java.classLoader
            .loadClass("pt.isel.Bar")
            .kotlin
//    val foo = barKlass.declaredFunctions.first { it.name == "foo" }
//    val barInstance: Any = barKlass.createInstance()
//    println("foo() = " + foo.call(barInstance))
    val bar = barKlass.createInstance() as IFoo
    println("foo() = ${bar.foo()}")
//
//    buildCounter()
//    val counterKlass =
//        Unit::class.java
//            .classLoader
//            .loadClass("pt.isel.Counter")
//            .kotlin
//    val counter =
//        counterKlass
//            .constructors
//            .first()
//            .call(17) as Sum
//    counter.add(11)
//    val nrValue =
//        counterKlass
//            .declaredMemberProperties
//            .first { it.name == "nr" }
//            .also { it.isAccessible = true }
//            .call(counter)
//    println(nrValue)
}

interface Sum {
    fun add(other: Int): Int
}

/*
private final int nr;

  public pt.isel.CounterBaseline(int);
    Code:

  public final int add(int);
    Code:
 */
fun buildCounter() {
    val className = "pt.isel.Counter"
    val sum = ClassDesc.of(Sum::class.qualifiedName)
    val bytes: ByteArray =
        ClassFile.of().build(ClassDesc.of(className)) { clb ->
            clb
                .withFlags(ACC_PUBLIC or ACC_FINAL)
                .withInterfaces(ofSymbols(sum).interfaces())
                .withField("nr", CD_int, ACC_PRIVATE)
                .withMethod(INIT_NAME, MethodTypeDesc.of(CD_void, CD_int), ACC_PUBLIC) { mb ->
                    mb.withCode { cob ->
                        // 0: aload_0
                        // 1: invokespecial #11                 // Method java/lang/Object."<init>":()V
                        // 4: aload_0
                        // 5: iload_1
                        // 6: putfield      #15                 // Field nr:I
                        // 9: return
                        cob
                            .aload(0)
                            .invokespecial(CD_Object, INIT_NAME, MTD_void)
                            .aload(0)
                            .iload(1)
                            .putfield(ClassDesc.of(className), "nr", CD_int)
                            .return_()
                    }
                }.withMethod("add", MethodTypeDesc.of(CD_int, CD_int), ACC_PUBLIC) { mb ->
                    mb.withCode { cob ->
                        cob
                            .aload(0)
                            .dup()
                            .getfield(ClassDesc.of(className), "nr", CD_int)
                            .iload(1)
                            .iadd()
                            .putfield(ClassDesc.of(className), "nr", CD_int)
                            .aload(0)
                            .getfield(ClassDesc.of(className), "nr", CD_int)
                            .ireturn()
                    }
                }
        }
    val resourcePath =
        Unit::class.java
            .getResource("/")
            ?.toURI()
            ?.path

    File(resourcePath, className.replace('.', '/') + ".class")
        .also { it.parentFile.mkdirs() } // Create directories if they do not exist
        .writeBytes(bytes)
}
