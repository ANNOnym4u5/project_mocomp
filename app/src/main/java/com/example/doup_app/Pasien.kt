package com.example.doup_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pasien(
    override val nama:String,
    override val user:String,
    override val pass:String,
    val email:String,
    val nomor:Int,
    var tertolak:Boolean
):Parcelable, User(nama, user, pass) {
    override fun toString(): String {
        return super.toString()
    }

}
