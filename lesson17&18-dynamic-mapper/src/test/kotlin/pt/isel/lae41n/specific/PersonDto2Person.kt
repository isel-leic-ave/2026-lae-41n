package pt.isel.lae41n.specific

import pt.isel.lae41n.BaseMapper
import pt.isel.lae41n.Mapper
import pt.isel.lae41n.domain.Person
import pt.isel.lae41n.domain.PersonDto

class PersonDto2Person : BaseMapper<PersonDto, Person>() {
    override fun mapFrom(personDto: PersonDto): Person {
        return Person(personDto.name, personDto.from)
    }
}