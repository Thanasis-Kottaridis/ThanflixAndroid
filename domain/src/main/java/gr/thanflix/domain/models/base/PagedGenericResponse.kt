package gr.thanflix.domain.models.base

data class PagedGenericResponse<T>(
    var results: T? = null,
    var page: Int? = null,
    var total_pages: Int? = null,
    var total_results: Int? = null
) {
}