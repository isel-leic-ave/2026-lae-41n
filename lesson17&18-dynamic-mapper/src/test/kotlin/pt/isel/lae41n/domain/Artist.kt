package pt.isel.lae41n.domain

import pt.isel.lae41n.AlternativeName


class Artist(
    val kind: String,
    val name: String,
    @AlternativeName("state") val country: Country,
    //@AlternativeName("songs") map: List<Track>
) {

}

class Country(val name: String, val idiom: String) {
}
