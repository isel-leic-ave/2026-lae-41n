package pt.isel.lae41n

/**
 * Mapper from PersonDto to Person
 */
fun PersonDto.toPerson(): pt.isel.lae41n.Person =
    Person(
        name = this.name,
        country = this.from,
    )
