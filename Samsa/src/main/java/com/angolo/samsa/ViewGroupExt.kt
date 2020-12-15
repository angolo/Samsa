package com.angolo.samsa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

infix fun ViewGroup.inflate(@LayoutRes lyt: Int) =
    LayoutInflater.from(context).inflate(lyt, this, false)!!