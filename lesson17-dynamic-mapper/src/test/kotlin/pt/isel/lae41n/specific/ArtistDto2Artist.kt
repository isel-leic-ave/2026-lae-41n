package pt.isel.lae41n.specific

import pt.isel.lae41n.Mapper
import pt.isel.lae41n.domain.Artist
import pt.isel.lae41n.domain.ArtistDto
import pt.isel.lae41n.domain.Country
import pt.isel.lae41n.domain.StateDto
import pt.isel.lae41n.loadDynamicMapper

class ArtistDto2Artist : Mapper<ArtistDto, Artist> {
    override fun mapFrom(artistDto: ArtistDto): Artist {
        val kind = artistDto.kind
        val name = artistDto.name
        val stateDto2CountryMapper = loadDynamicMapper(StateDto::class.java, Country::class.java)
        val country = stateDto2CountryMapper.mapFrom(artistDto.state)
        return Artist(kind, name, country)
    }
}