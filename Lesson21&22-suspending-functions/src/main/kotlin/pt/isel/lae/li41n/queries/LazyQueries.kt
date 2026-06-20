package pt.isel.lae.li41n.queries

import kotlin.coroutines.Continuation
import kotlin.sequences.SequenceScope

class LazyQueries {

    /**
     * Returns a sequence containing only elements matching the given [predicate].
     * Simplistic implementation that does not work with nullable items.
     */
    fun <T> Sequence<T>.lazyFilterGenerator(predicate: (T) -> Boolean): Sequence<T> {
//
        return sequence(mySequenceGenerator(this@lazyFilterGenerator, predicate))
    }
}
fun <T>mySequenceGenerator(upstream: Sequence<T>, predicate: (T) -> Boolean): suspend SequenceScope<T>.() -> Unit {
//return sequence {
////            for (item in this@lazyFilterGenerator) {
////                if (predicate(item)) {
////                    yield(item)
////                }
////            }
////        }

    fun SequenceScope<T>.myIternalSequenceGenerator(continuation: Continuation<Unit>): Any {
        var yieldCps = this::yield as (value: T, Continuation<Unit>) -> Any
        yieldCps()
    }

    return SequenceScope<T>::myIternalSequenceGenerator as suspend SequenceScope<T>.() -> Unit


//        val iter = upstream.iterator()
//        var nextValue = iter.next()
//        val yieldCps = ::yield as (Result<T>, Continuation<T>) -> Unit
//
//        val myContinuation = object : Continuation<T> {
//            override val context: CoroutineContext = EmptyCoroutineContext
//            override fun resumeWith(result: Result<T>) {
//                if(iter.hasNext()) {
//                    yieldCps(Result.success(iter.next()), myContinuation)
//
//                }
//            }
//        }
//
//        yieldCps(Result.success(nextValue), myContinuation)


//    }

//    val seqGenerator = SequenceScope<T>::myIternalSequenceGenerator as (suspend SequenceScope<T>.() -> Unit)
//}
}
