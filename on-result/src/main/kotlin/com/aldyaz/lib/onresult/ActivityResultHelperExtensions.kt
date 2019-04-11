package com.aldyaz.lib.onresult

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity


/**
 * @author Aldyaz on 26/08/18.
 */

fun FragmentActivity.startActivityForResult(
        intent: Intent,
        requestCode: Int,
        callback: ActivityResultCallback.() -> Unit
) {
    val activityResultCallback = ActivityResultCallback().apply(callback)
    startActivityForResult(intent, ActivityResultMapper.putCallback(requestCode, activityResultCallback))
}

fun Fragment.startActivityForResult(
        intent: Intent,
        requestCode: Int,
        callback: ActivityResultCallback.() -> Unit
) {
    val activityResultCallback: ActivityResultCallback = ActivityResultCallback().apply(callback)
    startActivityForResult(intent, ActivityResultMapper.putCallback(requestCode, activityResultCallback))
}

fun handleActivityResultCallback(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
) {
    val callback = ActivityResultMapper.getCallback(requestCode)
    callback?.onResult?.invoke(resultCode, data)
}