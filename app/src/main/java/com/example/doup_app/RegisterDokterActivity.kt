package com.example.doup_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResult

class RegisterDokterActivity : AppCompatActivity() {
    lateinit var etnama:EditText
    lateinit var etuser:EditText
    lateinit var etpass:EditText
    lateinit var etconfirm:EditText
    lateinit var spinner:Spinner
    lateinit var etdesc:EditText
    lateinit var ettahun:EditText
    lateinit var btnregis:Button
    lateinit var btnlogin:Button
    lateinit var users:ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_dokter)
        etnama=findViewById(R.id.etregisdokternama)
        etuser=findViewById(R.id.etregisdokteruser)
        etpass=findViewById(R.id.etregisdokterpass)
        etconfirm=findViewById(R.id.etregisdokterconfirm)
        spinner=findViewById(R.id.spinregisdokter)
        etdesc=findViewById(R.id.etregisdokterdesc)
        ettahun=findViewById(R.id.etregisdoktertahun)
        btnregis=findViewById(R.id.btnregisdokter)
        users=intent.getParcelableArrayListExtra<User>("user")!!
        btnregis.setOnClickListener {
            val nama=etnama.text.toString()
            val user=etuser.text.toString()
            val pass=etpass.text.toString()
            val conf=etconfirm.text.toString()
            val spesialisasi=spinner.selectedItem.toString()
            val desc=etdesc.text.toString()
            val tahun=ettahun.text.toString()
            if (nama.isNotEmpty()&&user.isNotEmpty()&&pass.isNotEmpty()&&conf.isNotEmpty()&&desc.isNotEmpty()&&tahun.isNotEmpty()){
                if (pass==conf){
                    users.add(Dokter("dr. $nama",user,pass,spesialisasi,desc,2022-tahun.toInt(),0,0,
                        arrayListOf()))
                    etconfirm.text.clear()
                    etnama.text.clear()
                    etpass.text.clear()
                    etuser.text.clear()
                    ettahun.text.clear()
                    etdesc.text.clear()
                    spinner.setSelection(0)
                } else {
                    Toast.makeText(this,"Password dan confirm password tidak sama!",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Inputan tidak boleh kosong!",Toast.LENGTH_SHORT).show()
            }
        }
        btnlogin=findViewById(R.id.btndoktertologin)
        btnlogin.setOnClickListener {
            val i=Intent()
            i.putExtra("user",users)
            setResult(Activity.RESULT_OK, i)
            finish()
        }
    }
}