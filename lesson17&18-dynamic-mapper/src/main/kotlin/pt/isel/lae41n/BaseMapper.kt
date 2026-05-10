package pt.isel.lae41n

abstract class BaseMapper<T: Any, R: Any >: Mapper<T, R> {
    protected fun <T : Any, R : Any>createMapper(src: Class<T>, dst:Class<R>): Mapper<T, R> {
        return loadDynamicMapper(src, dst)
    }
}