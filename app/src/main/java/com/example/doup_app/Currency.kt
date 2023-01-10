package com.example.doup_app

import java.text.NumberFormat
import java.util.*

object Currency {

    fun Int.toRupiah():String{
        // digunakan untuk mengubah format angka menjadi format uang rupiah
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("in","ID"))
        return numberFormat.format(this)
    }
}