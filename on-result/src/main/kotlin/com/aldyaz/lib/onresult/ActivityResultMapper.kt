package com.aldyaz.lib.onresult

import android.util.SparseArray


/**
 * @author Aldyaz on 26/08/18.
 */
object ActivityResultMapper {
    private val callbackArray: SparseArray<ActivityResultCallback> = SparseArray()

    fun putCallback(
            requestCode: Int,
            callback: ActivityResultCallback
    ): Int {
        callbackArray.put(requestCode, callback)
        return requestCode
    }

    fun getCallback(requestCode: Int): ActivityResultCallback? {
        return callbackArray[requestCode]?.also {
            callbackArray.remove(requestCode)
        }
    }
}