package pt.isel.lae41n

import pt.isel.lae41n.domain.Person
import pt.isel.lae41n.domain.PersonDto

import kotlin.test.Test
import kotlin.test.assertEquals


abstract class MapperTest {
    abstract fun getMapper() : Mapper<PersonDto, Person>;
    @Test
    fun `Test mapping PersonDto to Person`() {
        val mapper = getMapper();
        val dto = PersonDto("Maria", "Portugal")
        val person = mapper.mapFrom(dto)
        assertEquals("Maria", person.name)
        assertEquals("Portugal", person.country)
        //assertEquals(2001, person.bornYear)
    }
}

class MapperDynamicTest: MapperTest() {
    override fun getMapper(): Mapper<PersonDto, Person> {
        return loadDynamicMapper(PersonDto::class, Person::class)
    }
}

class MapperReflectTest: MapperTest() {
    override fun getMapper(): Mapper<PersonDto, Person> {
        return loadMapper(PersonDto::class, Person::class) as Mapper<PersonDto, Person>
    }
}

