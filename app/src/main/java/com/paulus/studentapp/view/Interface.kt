package com.paulus.studentapp.view

import android.view.View

interface ButtonDetailClickListener {
    fun onButtonDetailClick(v: View)
}

interface ButtonBackClickListener {
    fun onButtonBackClick(v: View)
}

interface ButtonUpdateClickListener {
    fun onButtonUpdateClick(v: View)
}

interface ButtonNotifClickListener {
    fun onButtonNotifClick(v: View)
}