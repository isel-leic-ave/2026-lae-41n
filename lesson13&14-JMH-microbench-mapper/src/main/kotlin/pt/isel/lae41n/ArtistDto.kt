package pt.isel.lae41n

class ArtistDto(
    val name: String,
    val kind: String,
    @AlternativeName("country") val state: StateDto,
    //@AlternativeName("tracks") val songs: List<pt.isel.lae41n.SongDto>,
)
