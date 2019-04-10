# OnResult
Inline-style activity result

ActivityResult
-----
```kotlin
startActivityForResult(
        Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),
        OPEN_BLUETOOTH_SETTING,
        callback = {
            onResult { resultCode, intent ->
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        // Do something when bluetooth is enabled
                    }
                    Activity.RESULT_CANCELED -> {
                        // Do something when user deny to enabling bluetooth
                    }
                }
            }
        }
)
```

Don't forget to invoke `handleActivityResultCallback`
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    handleActivityResultCallback(requestCode, resultCode, data)
}
```

Backlog
-----
 * Handler for `onRequestPermissionsResult`