package com.angolo.samsa

import android.app.Activity

public inline fun <T : androidx.viewbinding.ViewBinding> Activity.viewBinding
        (crossinline bindingInflater: (android.view.LayoutInflater) -> T) =

        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }

