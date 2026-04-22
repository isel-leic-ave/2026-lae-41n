package pt.isel.lae41n

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import pt.isel.lae41n.toPerson
import java.util.concurrent.TimeUnit

/**
 * ./gradlew jmhJar
 * java -jar lesson13-JMH-microbench-mapper/build/libs/lesson13-JMH-microbench-mapper-jmh.jar -i 4 -wi 4 -f 1 -r 2 -w 2
 */
@BenchmarkMode(Mode.AverageTime) // Measure execution time per operation
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
open class BenchMapper {
    private val ze = PersonDto("Ze Manel", "Portugal")

    private val muse =
        ArtistDto(
            "Muse",
            "Band",
            StateDto("UK", "en-UK"),
//            listOf(
//                SongDto("Starlight", 2006),
//                SongDto("Uprising", 2009),
//                SongDto("Madness", 2012),
//            ).sortedBy { it.title },
        )

    private val personMapper = Mapper(PersonDto::class, Person::class)
    private val artistMapper = Mapper(ArtistDto::class, Artist::class)

    @Benchmark
    fun mapperPersonBaseline(): Person = ze.toPerson()

    @Benchmark
    fun mapperPersonReflect(): Person = ze.mapTo(Person::class) as Person

    @Benchmark
    fun mapperPersonMapper(): Person = personMapper.mapTo(ze) as Person



//    @Benchmark
//    fun mapperPersonReflectOpt(): Person = personMapper.mapFrom(ze) as Person

    @Benchmark
    fun mapperArtistBaseline(): Artist = muse.toArtist()

    @Benchmark
    fun mapperArtistReflect(): Artist = muse.mapTo(Artist::class) as Artist

    @Benchmark
    fun mapperArtistMapper(): Artist = artistMapper.mapTo(muse) as Artist

//    @Benchmark
//    fun mapperArtistReflectOpt(): Artist = artistMapper.mapFrom(muse) as Artist
}
