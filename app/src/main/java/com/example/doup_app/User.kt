package com.example.doup_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

abstract class User(
    open val nama:String,
    open val user:String,
    open val pass:String,
    open val email: String,
    open val nomor: Int
):Parcelable{}