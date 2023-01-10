package com.example.doup_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener

class PasienActivity : AppCompatActivity() {
    lateinit var tvnama:TextView
    lateinit var users:ArrayList<User>
    lateinit var dokter:ArrayList<Dokter>
    lateinit var user:Pasien
    var idx=-1
    lateinit var btnlogout:Button
    lateinit var etnama:EditText
    lateinit var spin:Spinner
    lateinit var lv:ListView
    lateinit var la:DokterAdapter
    lateinit var btnkonsul:Button
    lateinit var tvtolak:TextView
    lateinit var lk:ActivityResultLauncher<Intent>
    lateinit var lc:ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasien)
        tvnama=findViewById(R.id.tvpasiennama)
        users=intent.getParcelableArrayListExtra<User>("user")!!
        idx=intent.getStringExtra("idx")!!.toInt()
        user=users[idx]as Pasien
        tvnama.text="Hi, ${user.nama}"
        btnlogout=findViewById(R.id.btnpasienlogout)
        btnlogout.setOnClickListener {
            users[idx]=user
            Merge()
            val i=Intent()
            i.putParcelableArrayListExtra("user",users)
            setResult(Activity.RESULT_OK,i)
            finish()
        }
        lv=findViewById(R.id.lvpasien)
        etnama=findViewById(R.id.etnamadokter)
        etnama.addTextChangedListener { text ->
            FilterDokter()
        }
        spin=findViewById(R.id.spinpasien)
        spin.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                FilterDokter()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        dokter= arrayListOf()
        UbahData()
        lk=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val data=it.data
            if (it.resultCode== RESULT_OK && data!=null){
                dokter=data.getParcelableArrayListExtra("dokter")!!
                Merge()
                CekKonsultasi()
                la.notifyDataSetChanged()
                BukaChat()
            }
        }
        lc=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val data=it.data
            if (data!=null){
                users=data.getParcelableArrayListExtra("user")!!
                UbahData()
                CekKonsultasi()
            }
        }
        btnkonsul=findViewById(R.id.btntchat)
        btnkonsul.setOnClickListener { BukaChat() }
        tvtolak=findViewById(R.id.tvtolak)
        CekKonsultasi()
    }

    private fun BukaChat() {
        for (u in users){
            if (u is Dokter){
                for (k in u.konsultasi){
                    if (k.pasien.user==user.user){
                        val intent=Intent(this@PasienActivity,ChatPasienActivity::class.java)
                        intent.putExtra("user",users)
                        intent.putExtra("idd",users.indexOf(u))
                        intent.putExtra("idk",u.konsultasi.indexOf(k))
                        intent.putExtra("login","pasien")
                        lc.launch(intent)
                    }
                }
            }
        }
    }

    private fun Merge() {
        for (u in users){
            if (u is Dokter){
                for (d in dokter){
                    if (u.user==d.user){
                        users[users.indexOf(u)]=d
                    }
                }
            }
        }
    }

    private fun FilterDokter() {
        UbahData()
        la.notifyDataSetChanged()
    }

    private fun CekTolak() {
        tvtolak.isVisible=user.tertolak
        user.tertolak=false
    }

    private fun CekKonsultasi() {
        var konsul=false
        for (d in dokter){
            for (k in d.konsultasi){
                if (k.pasien.user == user.user) {
                    konsul=true
                }
            }
        }
        la= DokterAdapter(this,user,dokter,!konsul,lk)
        lv.adapter=la
        btnkonsul.isEnabled=konsul
        CekTolak()
    }

    private fun UbahData() {
        dokter.clear()
        for (u in users){
            if (u is Dokter && u.biaya>0){
                val nama=etnama.text.toString()
                val id=spin.selectedItem
                if (nama.isEmpty()||nama in u.nama){
                    if (id=="Semua"||id==u.spesialisasi){
                        dokter.add(u)
                    }
                }
            }
        }
    }
}