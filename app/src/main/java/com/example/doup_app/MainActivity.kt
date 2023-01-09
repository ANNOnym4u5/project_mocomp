package com.example.doup_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    lateinit var etuser: EditText
    lateinit var etpass: EditText
    lateinit var btnLogin : Button
    lateinit var btnToRegis : Button
    lateinit var l: ActivityResultLauncher<Intent>
    var users:ArrayList<User> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etuser = findViewById(R.id.edUser)
        etpass = findViewById(R.id.edPass)
        btnLogin = findViewById(R.id.btnLogin)
        btnToRegis = findViewById(R.id.btnToRegis)

        btnLogin.setBackgroundColor(resources.getColor(R.color.orange))
        btnToRegis.setBackgroundColor(resources.getColor(R.color.orange))

        l=registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val data = it.data
            if (data !=null) {
                users=data.getParcelableArrayListExtra<User>("user")!!
            }
        }
        btnToRegis.setOnClickListener {
            val i= Intent(this,register_page::class.java)
            i.putExtra("user",users)
            l.launch(i)
        }
        btnLogin.setOnClickListener {
            val user=etuser.text.toString()
            val pass=etpass.text.toString()
            if (user.isNotEmpty()&&pass.isNotEmpty()){
                var login=false
                for (u in users){
                    if (u.user==user&&u.pass==pass){
                        login=true
//                        if (u is Dokter){
//                            val i=Intent(this,DokterActivity::class.java)
//                            i.putParcelableArrayListExtra("user",users)
//                            i.putExtra("idx",users.indexOf(u).toString())
//                            l.launch(i)
//                        }else{
//                            val i=Intent(this,PasienActivity::class.java)
//                            i.putParcelableArrayListExtra("user",users)
//                            i.putExtra("idx",users.indexOf(u).toString())
//                            l.launch(i)
//                        }
                        val i=Intent(this,PasienActivity::class.java)
                        i.putParcelableArrayListExtra("user",users)
                        i.putExtra("idx",users.indexOf(u).toString())
                        l.launch(i)
                    }
                }
                if (!login){
                    Toast.makeText(this,"Username dan Password tidak sesuai!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Inputan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}