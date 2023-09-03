package gr.thanflix.presentation.utils.helpers

import android.os.Handler
import android.os.Looper

fun withDelay(delay: Long, block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(kotlinx.coroutines.Runnable(block), delay)
}