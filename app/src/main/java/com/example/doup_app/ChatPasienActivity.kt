package com.example.doup_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible

class ChatPasienActivity : AppCompatActivity() {
    lateinit var user:ArrayList<User>
    lateinit var konsul:Konsultasi
    lateinit var login:String
    var idd=-1
    var idk=-1
    lateinit var btnback:Button
    lateinit var btnselesai:Button
    lateinit var lv:ListView
    lateinit var la:ChatAdapter
    lateinit var etpesan:EditText
    lateinit var btnsend:Button
    lateinit var tvdata:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_pasien)
        user=intent.getParcelableArrayListExtra("user")!!
        login=intent.getStringExtra("login")!!
        idd=intent.getIntExtra("idd",-1)
        val dokter=user[idd]as Dokter
        idk=intent.getIntExtra("idk",-1)
        konsul=dokter.konsultasi[idk]
        btnback=findViewById(R.id.btnchatback)
        tvdata=findViewById(R.id.tvdatapasien)
        tvdata.text="Nama Pasien : ${konsul.pasien.nama}\n" +
                "Keluhan : ${konsul.keluhan}"
        btnback.setOnClickListener {
            val i=Intent()
            i.putParcelableArrayListExtra("user",user)
            setResult(Activity.RESULT_OK,i)
            finish()
        }
        btnselesai=findViewById(R.id.btnselesai)
        if (login=="dokter"){
            btnselesai.isVisible=false
            btnselesai.isEnabled=false
        }else{
            btnselesai.setOnClickListener {
                dokter.konsultasi.removeAt(idk)
                dokter.pendapatan+=dokter.biaya
                val i=Intent()
                i.putExtra("user",user)
                setResult(Activity.RESULT_OK,i)
                finish()
            }
        }
        la= ChatAdapter(this,konsul.message,login)
        lv=findViewById(R.id.lvchat)
        lv.adapter=la
        etpesan=findViewById(R.id.etpesan)
        btnsend=findViewById(R.id.btnsend)
        btnsend.setOnClickListener {
            if (login=="dokter"){
                konsul.message.add(Msg(dokter.nama,"dokter",etpesan.text.toString()))
            }else{
                konsul.message.add(Msg(konsul.pasien.nama,"pasien",etpesan.text.toString()))
            }
            la.notifyDataSetChanged()
        }
    }
}