package com.aldyaz.onresult

import android.bluetooth.BluetoothAdapter


/**
 * @author Aldyaz on 10/04/19.
 */

inline fun checkBluetoothAvailability(
        onBluetoothAvailable: () -> Unit,
        onBluetoothNotAvailable: () -> Unit
) {
    val btAdapter = BluetoothAdapter.getDefaultAdapter()
    if (btAdapter != null) {
        onBluetoothAvailable()
    } else {
        onBluetoothNotAvailable()
    }
}