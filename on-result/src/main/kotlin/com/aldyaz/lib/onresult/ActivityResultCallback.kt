package com.aldyaz.lib.onresult

import android.content.Intent


/**
 * @author Aldyaz on 26/08/18.
 */
class ActivityResultCallback {
    internal var onResult: ((Int, Intent?) -> Unit)? = null

    fun onResult(result: (resultCode: Int, intent: Intent?) -> Unit) {
        this.onResult = result
    }
}