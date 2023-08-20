package gr.thanflix.data.network.mappers.series

import gr.thanflix.data.network.dto.series.SeasonDto
import gr.thanflix.domain.models.show.Show
import gr.thanflix.domain.utils.helpers.DomainMapper

class SeriesSeasonMapper : DomainMapper<SeasonDto, Show> {

    override fun modelToDomain(model: SeasonDto): Show {
        return Show(
            id = model.id,
            posterPath = model.posterPath,
            releaseDate = model.airDate,
            title = model.name,
            voteAverage = model.voteAverage
        )
    }

    override fun domainToModel(domainModel: Show): SeasonDto {
        return SeasonDto()
    }
}