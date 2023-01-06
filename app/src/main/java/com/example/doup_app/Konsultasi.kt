package com.example.doup_app

import android.os.Message
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Konsultasi(
    val pasien: Pasien,
    val keluhan: String,
    val message:ArrayList<Msg>
):Parcelable {
    override fun toString(): String {
        return "Nama : ${pasien.nama}\nKeluhan : $keluhan"
    }
}