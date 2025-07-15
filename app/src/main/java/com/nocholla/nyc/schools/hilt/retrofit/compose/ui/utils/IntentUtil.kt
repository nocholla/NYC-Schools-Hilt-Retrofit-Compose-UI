package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

object IntentUtil {

    private const val TAG = "INTENT_UTIL"

    @JvmStatic
    fun openIntentWithUriAndAction(context: Context?, uri: Uri?, action: String?) {
        context?.safeStart(Intent(action).apply { data = uri })
    }

    @JvmStatic
    fun openIntentChooser(context: Context, intent: Intent, chooserTitle: String) {
        context.startActivity(Intent.createChooser(intent, chooserTitle))
    }

    @JvmStatic
    fun openUrlIntent(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    @JvmStatic
    fun Context.safeStart(intent: Intent, onFailure: (() -> Unit)? = null) {
        try {
            startActivity(intent)
        } catch (e : Exception) {
            Log.d(TAG, "Intent with action ${intent.action} could not resolve a target activity")
            onFailure?.invoke()
        }
    }

}