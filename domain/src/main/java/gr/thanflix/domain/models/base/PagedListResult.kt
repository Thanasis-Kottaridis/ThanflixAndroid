package gr.thanflix.domain.models.base

data class PagedListResult<T>(
    val results: List<T> = emptyList(),
    val next: Int? = null,
    val previous: Int? = null,
    val current: Int? = null,
    val total: Int? = null
)