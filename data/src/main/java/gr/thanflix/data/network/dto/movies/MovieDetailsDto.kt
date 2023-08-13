package gr.thanflix.data.network.dto.movies

import gr.thanflix.data.network.dto.common.GenreDto
import gr.thanflix.data.network.dto.common.ProductionCompanyDto
import gr.thanflix.data.network.dto.common.ProductionCountryDto
import gr.thanflix.data.network.dto.common.SpokenLanguageDto

data class MovieDetailsDto(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val belongsToCollection: MovieCollectionDto? = null,
    val budget: Int? = null,
    val genres: List<GenreDto>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdbID: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val productionCompanies: List<ProductionCompanyDto>? = null,
    val productionCountries: List<ProductionCountryDto>? = null,
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val spokenLanguages: List<SpokenLanguageDto>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)
