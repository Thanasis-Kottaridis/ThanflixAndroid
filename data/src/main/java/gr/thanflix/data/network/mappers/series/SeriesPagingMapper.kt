package gr.thanflix.data.network.mappers.series

import gr.thanflix.data.network.dto.series.SeriesDto
import gr.thanflix.domain.models.show.Show
import gr.thanflix.domain.utils.helpers.PagingDataDomainMapper

class SeriesPagingMapper : PagingDataDomainMapper<SeriesDto, Show> {
    override fun modelToDomain(model: SeriesDto): Show {
        return Show(
            id = model.id,
            posterPath = model.posterPath,
            releaseDate = model.firstAirDate,
            title = model.name,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount
        )
    }

    override fun domainToModel(domainModel: Show): SeriesDto {
        return SeriesDto() // You'll need to properly map the Show model to SeriesDto here
    }
}
