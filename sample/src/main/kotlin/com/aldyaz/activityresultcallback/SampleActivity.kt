package com.aldyaz.activityresultcallback

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.lib.activityresultcallback.handleActivityResultCallback
import com.lib.activityresultcallback.startActivityForResult
import kotlinx.android.synthetic.main.activity_sample.*


/**
 * @author Aldyaz on 27/08/18.
 */
class SampleActivity : AppCompatActivity() {

    companion object {
        const val OPEN_BLUETOOTH_SETTING = 1234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
    }

    override fun onStart() {
        super.onStart()
        checkBluetoothAvailability(
                onBluetoothAvailable = this::checkBluetoothState,
                onBluetoothNotAvailable = {
                    tv_bluetooth_state?.text = "Your device not supporting bluetooth"
                }
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        handleActivityResultCallback(requestCode, resultCode, data)
    }

    private fun checkBluetoothState() {
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled) {
            startActivityForResult(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),
                    OPEN_BLUETOOTH_SETTING,
                    callback = {
                        onResult { resultCode, intent ->
                            when (resultCode) {
                                Activity.RESULT_OK -> {
                                    setBluetoothEnableState(true)
                                    intent?.also {
                                        Log.d("BluetoothIntent", "data: ${it.data}")
                                    }
                                }
                                Activity.RESULT_CANCELED -> setBluetoothEnableState(false)
                            }
                        }
                    }
            )
        } else {
            setBluetoothEnableState(true)
        }
    }

    private fun setBluetoothEnableState(isBluetoothEnabled: Boolean) {
        val text = "Bluetooth State is: ${if (isBluetoothEnabled) "ON" else "OFF"}"
        tv_bluetooth_state?.text = text
    }

}