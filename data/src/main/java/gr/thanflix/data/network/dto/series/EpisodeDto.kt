package gr.thanflix.data.network.dto.series

data class EpisodeDto(
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val airDate: String? = null,
    val episodeNumber: Int? = null,
    val productionCode: String? = null,
    val runtime: Int? = null,
    val seasonNumber: Int? = null,
    val showID: Int? = null,
    val stillPath: String? = null
)