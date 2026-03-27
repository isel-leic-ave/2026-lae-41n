package pt.isel.mapper

import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class MapTo(val destName: String)

data class ADto(@MapTo("a") val x: Int = 0, val y: Int = 0)
data class ADomain(val a: Int = 0, val y: Int = 0)

fun mapADtoToADomain(adto: ADto): ADomain {
    return ADomain(a = adto.x, y = adto.y)
}

fun mapTo(src: Any, dstType: KClass<*>) : Any {
    // Obtain the source type KClass representative
    val srcType = src::class
    val primaryConstructor = dstType.primaryConstructor!!
    // Para cada parâmetro do construtor primário, obter o valor da
    // propriedade com o mesmo nome no objeto src

    // For each primary constructor parameter, obtain the value of the
    // property with the same name in src object
    var argumentsMap : Map<KParameter, Any> =
        primaryConstructor.parameters.associateWith {
            parameter ->
                val propSrc: KProperty1<Any, Any>? = srcType.memberProperties.find {
                    property ->
                        property.returnType
                        if(property.name == parameter.name)
                            return@find true
                        val mapToAnnotation = property.findAnnotation<MapTo>()
                        if(mapToAnnotation == null)
                            return@find false
                        return@find mapToAnnotation.destName == parameter.name


                } as KProperty1<Any, Any>?
                // Goal: return the value corresponding to the parameter argument
                return@associateWith propSrc!!.call(src)
        }
    // Obtain the primary constructor for the destination type and call it with all the
    // arguments in argumentsMap

    return primaryConstructor.callBy(argumentsMap)
}


fun main() {
    val adto = ADto(5, -3)
    val adomain1 = mapADtoToADomain(adto)
    println(adomain1)

    var adomain2 = mapTo(adto, ADomain::class) as ADomain
    println(adomain2)
}