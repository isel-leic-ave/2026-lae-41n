package pt.isel.lae41n.domain

import pt.isel.lae41n.AlternativeName


class PersonDto(
    val name: String,
    @AlternativeName("country") val from: String,
)

