package gr.thanflix.domain.models.base

sealed class Result<out T, out E> {
    data class Success<out T>(val body: T) : Result<T, Nothing>()
    data class Failure<out E>(val errorBody: E) : Result<Nothing, E>()
}