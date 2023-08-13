package gr.thanflix.data.network.mappers.movies

import gr.thanflix.data.network.dto.movies.MovieDetailsDto
import gr.thanflix.data.network.mappers.common.ProductionCompaniesMapper
import gr.thanflix.domain.models.show.Overview
import gr.thanflix.domain.models.show.ShowDetails
import gr.thanflix.domain.utils.helpers.DomainMapper
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Currency
import java.util.Locale

class MovieDetailsMapper : DomainMapper<MovieDetailsDto, ShowDetails> {

    override fun modelToDomain(model: MovieDetailsDto): ShowDetails {
        return ShowDetails(
            id = model.id,
            posterPath = model.posterPath,
            backdropPath = model.backdropPath,
            releaseDate = convertDate(model.releaseDate),
            title = model.title,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount,
            popularity = model.popularity,
            overview = getShowOverview(model),
            productionCompanies = ProductionCompaniesMapper().mapDomainLists(model.productionCompanies ?: emptyList())
        )
    }

    override fun domainToModel(domainModel: ShowDetails): MovieDetailsDto {
        TODO("Not yet implemented")
    }

    private fun getShowOverview(model: MovieDetailsDto): List<Overview> {
        val showOverview: MutableList<Overview> = mutableListOf()

        // 1. add run time
        model.runtime?.let { value ->
            showOverview.add(
                Overview(
                    key = "OVERVIEW_RUNTIME",
                    value = formatMinutes(value)
                )
            )
        }

        // 2. add countries overview
        model.productionCountries?.let { value ->
            showOverview.add(
                Overview(
                    key = "OVERVIEW_COUNTRIES",
                    value = value.joinToString(",") { it.name }
                )
            )
        }

        // 3. Add language overview
        model.spokenLanguages?.let { value ->
            showOverview.add(
                Overview(
                    key = "OVERVIEW_LANGUAGE",
                    value = value.joinToString(",") { it.name }
                )
            )
        }

        // 4. Add Geners overview
        model.genres?.let { value ->
            showOverview.add(
                Overview(
                    key = "OVERVIEW_GENERS",
                    value = value.joinToString(",") { it.name }
                )
            )
        }

        // 5. Add Budget overview
        model.budget?.let { value ->
            showOverview.add(
                Overview(
                    key = "OVERVIEW_BUDGET",
                    value = formatAmountInDollars(value)
                )
            )
        }

        // 6. Add Revenue overview
        model.revenue?.let { value ->
            showOverview.add(
                Overview(
                    key = "OVERVIEW_REVENUE",
                    value = formatAmountInDollars(value)
                )
            )
        }

        // 7. Add Synopsis overview
        model.overview?.let { value ->
            showOverview.add(
                Overview(
                    key = "OVERVIEW_SYNOPSIS",
                    value = value
                )
            )
        }

        return showOverview
    }

    private fun formatAmountInDollars(amount: Int): String? {
        val formatter = NumberFormat.getCurrencyInstance()
        formatter.currency = Currency.getInstance("USD")
        return formatter.format(amount.toLong())
    }

    private fun convertDate(dateString: String?): String? {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        return try {
            val date = dateFormatter.parse(dateString)
            val newDateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
            newDateFormat.format(date)
        } catch (e: Exception) {
            null
        }
    }

    private fun formatMinutes(totalMinutes: Int): String {
        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60

        var formattedTime = ""
        if (hours > 0) {
            formattedTime += "$hours Hour"
            if (hours > 1) {
                formattedTime += "s"
            }
        }

        if (minutes > 0) {
            if (formattedTime.isNotEmpty()) {
                formattedTime += " "
            }
            formattedTime += "$minutes Minute"
            if (minutes > 1) {
                formattedTime += "s"
            }
        }

        return formattedTime
    }
}
