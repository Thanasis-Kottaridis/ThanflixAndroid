package gr.thanflix.data.network.mappers.movies

import gr.thanflix.data.network.dto.movies.MovieDto
import gr.thanflix.domain.models.show.Show
import gr.thanflix.domain.utils.helpers.DomainMapper
import gr.thanflix.domain.utils.helpers.PagingDataDomainMapper

class MoviePagingMapper : PagingDataDomainMapper<MovieDto, Show> {
    override fun modelToDomain(model: MovieDto): Show {
        return Show(
            id = model.id,
            posterPath = model.posterPath,
            releaseDate = model.releaseDate,
            title = model.title,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount
        )
    }

    override fun domainToModel(domainModel: Show): MovieDto {
        TODO("Not yet implemented")
    }
}