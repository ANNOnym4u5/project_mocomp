package com.example.doup_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dokter(
    val nama:String,
    val user:String,
    val pass:String,
    val spesialisasi:String,
    val desc:String,
    val pengalaman:Int,
    var biaya:Int,
    var pendapatan:Int,
    val konsultasi:ArrayList<Konsultasi>
):Parcelable{}