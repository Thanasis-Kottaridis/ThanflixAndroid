package gr.thanflix.data.network.mappers.series

import gr.thanflix.data.network.dto.series.SeriesDetailsDto
import gr.thanflix.data.network.mappers.common.ProductionCompaniesMapper
import gr.thanflix.domain.models.show.Overview
import gr.thanflix.domain.models.show.ShowDetails
import gr.thanflix.domain.utils.helpers.DomainMapper
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale

class SeriesDetailsMapper : DomainMapper<SeriesDetailsDto, ShowDetails> {

    override fun modelToDomain(model: SeriesDetailsDto): ShowDetails {
        return ShowDetails(
            id = model.id,
            posterPath = model.posterPath,
            backdropPath = model.backdropPath,
            releaseDate = convertDate(model.firstAirDate),
            title = model.name,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount,
            popularity = model.popularity,
            overview = getShowOverview(model),
            productionCompanies = ProductionCompaniesMapper().mapDomainLists(model.productionCompanies ?: emptyList()),
            seasons = SeriesSeasonMapper().mapDomainLists(model.seasons ?: emptyList())
        )
    }

    override fun domainToModel(domainModel: ShowDetails): SeriesDetailsDto {
        return SeriesDetailsDto()
    }

    private fun getShowOverview(model: SeriesDetailsDto): List<Overview> {
        val showOverview: MutableList<Overview> = mutableListOf()

        // 1. add number of seasons and episodes
        model.numberOfSeasons?.let { seasons ->
            model.numberOfEpisodes?.let { episodes ->
                showOverview.add(
                    Overview(
                        key = "OVERVIEW_SEASONS_AND_EPISODES",
                        value = "$seasons Seasons - $episodes Episodes"
                    )
                )
            }
        }

        // 2. add countries overview
        model.spokenLanguages?.let { languages ->
            showOverview.add(
                Overview(
                    key = "OVERVIEW_COUNTRIES",
                    value = languages.map { it.name }.joinToString(",")
                )
            )
        }

        // 3. Add language overview
        model.spokenLanguages?.let { languages ->
            showOverview.add(
                Overview(
                    key = "OVERVIEW_LANGUAGE",
                    value = languages.map { it.name }.joinToString(",")
                )
            )
        }

        // 4. Add Genres overview
        model.genres?.let { genres ->
            showOverview.add(
                Overview(
                    key = "OVERVIEW_GENRES",
                    value = genres.map { it.name }.joinToString(",")
                )
            )
        }

        // 5. Add Synopsis overview
        model.overview?.let { overview ->
            if (overview.isNotEmpty()) {
                showOverview.add(
                    Overview(
                        key = "OVERVIEW_SYNOPSIS",
                        value = overview
                    )
                )
            }
        }

        return showOverview
    }

    private fun convertDate(dateString: String?): String? {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        val date = try {
            dateString?.let { dateFormatter.parse(it) }
        } catch (e: Exception) {
            null
        }

        val targetDateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
        return date?.let {
            targetDateFormat.format(it)
        }
    }
}
