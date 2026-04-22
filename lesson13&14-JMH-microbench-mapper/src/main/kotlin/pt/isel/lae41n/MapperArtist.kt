package pt.isel.lae41n

import javax.sound.midi.Track

/**
 * Mapper from ArtistDto to Artist
 */
fun ArtistDto.toArtist(): Artist =
    Artist(
        kind,
        name,
        Country(state.name, state.idiom),
//        songs.map { Track(it.title, it.year) },
    )
