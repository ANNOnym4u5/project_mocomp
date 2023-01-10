package com.example.doup_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dokter(
    override val nama:String,
    override val user:String,
    override val pass:String,
    val spesialisasi:String,
    val desc:String,
    val pengalaman:Int,
    var biaya:Int,
    var pendapatan:Int,
    val konsultasi:ArrayList<Konsultasi>
):Parcelable,User(nama, user, pass){

}