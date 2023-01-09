package com.example.doup_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class register_page : AppCompatActivity() {
    lateinit var etuser: EditText
    lateinit var etpass: EditText
    lateinit var etconfirm: EditText
    lateinit var etemail: EditText
    lateinit var etnomor: EditText
    lateinit var btnregis: Button
    lateinit var btnlogin: Button
    lateinit var users:ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        etuser=findViewById(R.id.edUserReg)
        etpass=findViewById(R.id.edPassReg)
        etconfirm=findViewById(R.id.edConfReg)
        etemail=findViewById(R.id.edMailReg)
        etnomor=findViewById(R.id.edNumberReg)
        users=intent.getParcelableArrayListExtra<User>("user")!!
        btnregis=findViewById(R.id.btnRegister)
        btnlogin=findViewById(R.id.btnToLogin)
        btnlogin.setOnClickListener {
            val i= Intent()
            i.putExtra("user",users)
            setResult(Activity.RESULT_OK, i)
            finish()
        }
        btnregis.setOnClickListener {
            val nama="ga ada field nama bro :)"
            val user=etuser.text.toString()
            val pass=etpass.text.toString()
            val conf=etconfirm.text.toString()
            val email=etemail.text.toString()
            val nomor=etnomor.text.toString()
            if (nama.isNotEmpty()&&user.isNotEmpty()&&pass.isNotEmpty()&&conf.isNotEmpty()&&email.isNotEmpty()&&nomor.isNotEmpty()){
                if (pass==conf){
                    users.add(Pasien(nama,user,pass,email,nomor.toInt(),false))
                    etconfirm.text.clear()
//                    etnama.text.clear()
                    etpass.text.clear()
                    etuser.text.clear()
                    etemail.text.clear()
                    etnomor.text.clear()
                } else {
                    Toast.makeText(this,"Password dan confirm password tidak sama!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Inputan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}