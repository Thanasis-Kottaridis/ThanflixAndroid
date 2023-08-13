package gr.thanflix.domain.utils.helpers

import gr.thanflix.domain.models.base.PagedGenericResponse
import gr.thanflix.domain.models.base.PagedListResult

interface PagingDataDomainMapper<ApiResponse, DomainModel> :
    DomainMapper<ApiResponse, DomainModel> {

    fun domainToPagingData(
        response: PagedGenericResponse<List<ApiResponse>>?
    ): PagedListResult<DomainModel> {
        val totalPages = response?.total_pages ?: 0
        val cur = response?.page ?: 0
        val next = if (cur != totalPages) cur + 1 else cur
        val prev = cur - 1.coerceAtLeast(0)

        return PagedListResult(
            results = mapDomainLists(response?.results ?: emptyList()),
            next = if (next > cur) next else null,
            previous = if (prev < cur) prev else null,
            current = cur,
            total = response?.total_results
        )
    }
}