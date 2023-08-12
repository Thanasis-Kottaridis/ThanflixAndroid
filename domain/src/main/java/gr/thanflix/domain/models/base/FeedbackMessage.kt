package gr.thanflix.domain.models.base

sealed class FeedbackMessageType {
    object Success : FeedbackMessageType()
    object Error : FeedbackMessageType()
    object Info : FeedbackMessageType()
    data class Network(val isError: Boolean) : FeedbackMessageType()
}

data class FeedbackMessage(val message: String, val type: FeedbackMessageType)