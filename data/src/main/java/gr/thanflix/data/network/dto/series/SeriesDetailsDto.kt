package gr.thanflix.data.network.dto.series

import gr.thanflix.data.network.dto.common.GenreDto
import gr.thanflix.data.network.dto.common.ProductionCompanyDto
import gr.thanflix.data.network.dto.common.ProductionCountryDto
import gr.thanflix.data.network.dto.common.SpokenLanguageDto

data class SeriesDetailsDto(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val createdBy: List<CreatorDto>? = null,
    val episodeRunTime: List<Int>? = null,
    val firstAirDate: String? = null,
    val genres: List<GenreDto>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val inProduction: Boolean? = null,
    val languages: List<String>? = null,
    val lastAirDate: String? = null,
    val lastEpisodeToAir: EpisodeDto? = null,
    val name: String? = null,
    val nextEpisodeToAir: EpisodeDto? = null,
    val networks: List<NetworkDto>? = null,
    val numberOfEpisodes: Int? = null,
    val numberOfSeasons: Int? = null,
    val originCountry: List<String>? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val productionCompanies: List<ProductionCompanyDto>? = null,
    val productionCountries: List<ProductionCountryDto>? = null,
    val seasons: List<SeasonDto>? = null,
    val spokenLanguages: List<SpokenLanguageDto>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)
