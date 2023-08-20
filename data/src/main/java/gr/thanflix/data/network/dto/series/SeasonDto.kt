package gr.thanflix.data.network.dto.series

data class SeasonDto(
    val airDate: String? = null,
    val episodeCount: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val seasonNumber: Int? = null,
    val voteAverage: Double? = null
)