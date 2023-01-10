package com.example.doup_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.doup_app.Currency.toRupiah

class DokterActivity : AppCompatActivity() {
    lateinit var tvnama:TextView
    lateinit var users:ArrayList<User>
    var idx=-1
    lateinit var user:Dokter
    lateinit var tvpendapatan:TextView
    lateinit var lv:ListView
    lateinit var la:ArrayAdapter<Konsultasi>
    lateinit var btnlogout:Button
    lateinit var etbiaya:EditText
    lateinit var btnsave:Button
    var selected=-1
    lateinit var l:ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dokter)
        l=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val data=it.data
            if (data!=null){
                users=data.getParcelableArrayListExtra("user")!!
                user= users[idx] as Dokter
            }
        }
        tvnama=findViewById(R.id.tvnamadokter)
        users=intent.getParcelableArrayListExtra<User>("user")!!
        idx=intent.getStringExtra("idx")!!.toInt()
        user=users[idx]as Dokter
        tvnama.text="Hi, ${user.nama}"
        tvpendapatan=findViewById(R.id.tvpendapatan)
        tvpendapatan.text="Total Pendapatan : ${user.pendapatan.toRupiah()}"
        lv= findViewById(R.id.lvdokter)
        la=ArrayAdapter(this,android.R.layout.simple_list_item_1,user.konsultasi)
        lv.adapter=la
        lv.setOnItemClickListener { adapterView, view, i, l ->
            selected=i
            registerForContextMenu(view)
        }
        btnlogout=findViewById(R.id.btndokterlogout)
        btnlogout.setOnClickListener {
            users[idx]=user
            val i=Intent()
            i.putExtra("user",users)
            setResult(RESULT_OK,i)
            finish()
        }
        etbiaya=findViewById(R.id.etbiaya)
        etbiaya.setText(user.biaya.toString())
        btnsave=findViewById(R.id.btneditbiaya)
        btnsave.setOnClickListener {
            user.biaya=etbiaya.text.toString().toInt()
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menudetail->{
                val intent=Intent(this@DokterActivity,ChatPasienActivity::class.java)
                intent.putExtra("user",users)
                intent.putExtra("idd",idx)
                intent.putExtra("idk",selected)
                intent.putExtra("login","dokter")
                l.launch(intent)
            }
            R.id.menutolak->{
                var p=users[users.indexOf(user.konsultasi[selected].pasien)]as Pasien
                p.tertolak=true
                user.konsultasi.removeAt(selected)
                la.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)
    }
}