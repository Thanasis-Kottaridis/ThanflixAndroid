package gr.thanflix.domain.models.show

data class ShowDetails(
    val id: Int? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val popularity: Double? = null,
    val overview: List<Overview>? = null,
    val productionCompanies: List<ProductionCompany>? = null,
    val seasons: List<Show>? = null
)