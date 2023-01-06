package com.example.doup_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Msg(
    val sender:String,
    val tipe:String,
    val isi:String
):Parcelable{
    override fun toString(): String {
        return "${sender}\n$isi"
    }
}
