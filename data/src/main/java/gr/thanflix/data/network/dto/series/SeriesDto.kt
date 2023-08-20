package gr.thanflix.data.network.dto.series

data class SeriesDto(
    val backdropPath: String? = null,
    val firstAirDate: String? = null,
    val genreID: List<Int>? = null,
    val id: Int? = null,
    val name: String? = null,
    val originCountry: List<String>? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)