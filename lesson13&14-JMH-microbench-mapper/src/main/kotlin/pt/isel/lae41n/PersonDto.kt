package pt.isel.lae41n

class PersonDto(
    val name: String,
    @AlternativeName(name = "country") val from: String
)

