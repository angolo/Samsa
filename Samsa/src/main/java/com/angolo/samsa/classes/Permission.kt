package com.angolo.samsa.classes

import android.Manifest.permission.*

sealed class Permission(vararg val permissions: String) {
    // Individual permissions
    object Camera : Permission(CAMERA)
    object BodySensors: Permission(BODY_SENSORS)


    //multiple permissions
    object Storage : Permission( READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)

    object Location : Permission( ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)


}
