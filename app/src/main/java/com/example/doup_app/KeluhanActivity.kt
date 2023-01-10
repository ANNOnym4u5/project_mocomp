package com.example.doup_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.doup_app.Currency.toRupiah

class KeluhanActivity : AppCompatActivity() {
    lateinit var ld:ArrayList<Dokter>
    lateinit var user: Pasien
    var idx=-1
    lateinit var dokter:Dokter
    lateinit var tvnama:TextView
    lateinit var tvspesial:TextView
    lateinit var tvpengalaman:TextView
    lateinit var tvbiaya:TextView
    lateinit var multi:EditText
    lateinit var tvdesc:TextView
    lateinit var btnbatal:Button
    lateinit var btnmulai:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keluhan)
        ld=intent.getParcelableArrayListExtra("dokter")!!
        user=intent.getParcelableExtra("user")!!
        idx=intent.getIntExtra("idx",-1)
        dokter=ld[idx]
        tvnama=findViewById(R.id.tvkeluhannama)
        tvnama.text=dokter.nama
        tvspesial=findViewById(R.id.tvkeluhanspesialis)
        tvspesial.text="Spesialis : ${dokter.spesialisasi}"
        tvpengalaman=findViewById(R.id.tvkeluhanpengalaman)
        tvpengalaman.text="Menjadi dokter selama : ${dokter.pengalaman} tahun"
        tvdesc=findViewById(R.id.tvkeluhandesc)
        tvdesc.text="Deskripsi :\n${dokter.desc}"
        tvbiaya=findViewById(R.id.tvkeluhanbiaya)
        tvbiaya.text="Biaya : ${dokter.biaya.toRupiah()}"
        btnbatal=findViewById(R.id.btnkeluhanbatal)
        btnbatal.setOnClickListener {
            finish()
        }
        multi=findViewById(R.id.etkeluhan)
        btnmulai=findViewById(R.id.btnmulaikonsul)
        btnmulai.setOnClickListener {
            val keluhan=multi.text.toString()
            if (keluhan.isNotEmpty()){
                dokter.konsultasi.add(Konsultasi(user,keluhan, arrayListOf()))
                val i=Intent()
                i.putParcelableArrayListExtra("dokter",ld)
                i.putExtra("idx",idx)
                setResult(Activity.RESULT_OK,i)
                finish()
            }else{
                Toast.makeText(this,"Keluhan belum diisi!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}