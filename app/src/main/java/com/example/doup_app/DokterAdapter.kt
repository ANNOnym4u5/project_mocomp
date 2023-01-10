package com.example.doup_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import com.example.doup_app.Currency.toRupiah

class DokterAdapter(
    val c:Activity,
    val user:Pasien,
    val dokter:ArrayList<Dokter>,
    val konsul:Boolean,
    val l:ActivityResultLauncher<Intent>
):ArrayAdapter<Dokter>(c, R.layout.activity_lv_pasien, dokter) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=c.layoutInflater.inflate(R.layout.activity_lv_pasien,null,true)
        val tvnama=view.findViewById<TextView>(R.id.tvlvnama)
        val d=dokter[position]
        tvnama.text=d.nama
        val tvspesialisasi=view.findViewById<TextView>(R.id.tvlvspesialisasi)
        tvspesialisasi.text="Spesialis : ${d.spesialisasi}"
        val tvpengalaman=view.findViewById<TextView>(R.id.tvlvpengalaman)
        tvpengalaman.text="Pengalaman : ${d.pengalaman} tahun"
        val tvharga=view.findViewById<TextView>(R.id.tvlvbiaya)
        tvharga.text="Biaya : ${d.biaya.toRupiah()}"
        val btn=view.findViewById<Button>(R.id.btnlvlihat)
        btn.isEnabled=konsul
        btn.setOnClickListener {
            val i=Intent(c,KeluhanActivity::class.java)
            i.putExtra("user",user)
            i.putExtra("dokter",dokter)
            i.putExtra("idx",position)
            l.launch(i)
        }
        return view
    }
}