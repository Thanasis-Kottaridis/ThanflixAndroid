package gr.thanflix.presentation.components


import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gr.thanflix.domain.models.base.FeedbackMessageType
import gr.thanflix.presentation.R
import gr.thanflix.presentation.databinding.FeedbackMessageLayoutBinding
import gr.thanflix.presentation.utils.helpers.withDelay

/**
 * A class that builds a dialog for showing a top up message.
 * Implementation: TopMessage.build(FeedbackMessageType, message, context).show()
 */
class FeedbackMessageView(type: FeedbackMessageType, message: String, context: Context) {
    private var dialog: Dialog

    init {
        dialog = Dialog(context)
        val binding = FeedbackMessageLayoutBinding.inflate(LayoutInflater.from(context), null, false)
        binding.setUpTopMessage(type, message)
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setGravity(Gravity.TOP)
        dialog.window?.setBackgroundDrawableResource(R.color.transparent)
        dialog.setCancelable(false)
    }

    fun show() {
        dialog.show()
        withDelay(2000) {
            dialog.dismiss()
        }
    }

    companion object {
        fun build(type: FeedbackMessageType, message: String, context: Context): FeedbackMessageView =
            FeedbackMessageView(type, message, context)
    }
}

fun FeedbackMessageLayoutBinding.setUpTopMessage(type: FeedbackMessageType, message: String) {
    val view = this.feedbackMsgConstraint
    feedbackMsgTxtView.text = message
    warningImgView.setBackgroundResource(R.drawable.close)
    warningImgView.visibility = View.VISIBLE
    when (type) {
        FeedbackMessageType.Success ->
            feedbackMsgConstraint.setBackgroundColor(view.resources.getColor(R.color.tint_green))

        FeedbackMessageType.Error ->
            feedbackMsgConstraint.setBackgroundColor(view.resources.getColor(R.color.tint_red))

        FeedbackMessageType.Info ->
            feedbackMsgConstraint.setBackgroundColor(view.resources.getColor(R.color.fill_primary))

        is FeedbackMessageType.Network -> {
            if (type.isError)
                feedbackMsgConstraint.setBackgroundColor(view.resources.getColor(R.color.tint_red))
            else
                feedbackMsgConstraint.setBackgroundColor(view.resources.getColor(R.color.tint_green))
        }
    }
}