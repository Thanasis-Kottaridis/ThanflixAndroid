package gr.thanflix.data.network.dto.series

import com.google.gson.annotations.SerializedName
import gr.thanflix.data.network.dto.common.GenreDto
import gr.thanflix.data.network.dto.common.ProductionCompanyDto
import gr.thanflix.data.network.dto.common.ProductionCountryDto
import gr.thanflix.data.network.dto.common.SpokenLanguageDto

data class SeriesDetailsDto(
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("created_by") val createdBy: List<CreatorDto>? = null,
    @SerializedName("episode_run_time") val episodeRunTime: List<Int>? = null,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("genres") val genres: List<GenreDto>? = null,
    @SerializedName("homepage") val homepage: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("in_production") val inProduction: Boolean? = null,
    @SerializedName("languages") val languages: List<String>? = null,
    @SerializedName("last_air_date") val lastAirDate: String? = null,
    @SerializedName("last_episode_to_air") val lastEpisodeToAir: EpisodeDto? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("next_episode_to_air") val nextEpisodeToAir: EpisodeDto? = null,
    @SerializedName("networks") val networks: List<NetworkDto>? = null,
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int? = null,
    @SerializedName("number_of_seasons") val numberOfSeasons: Int? = null,
    @SerializedName("origin_country") val originCountry: List<String>? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanyDto>? = null,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountryDto>? = null,
    @SerializedName("seasons") val seasons: List<SeasonDto>? = null,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguageDto>? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("tagline") val tagline: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("vote_count") val voteCount: Int? = null
)