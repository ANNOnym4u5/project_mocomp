package com.example.doup_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    lateinit var btnLogin : Button
    lateinit var btnToRegis : Button
    lateinit var l: ActivityResultLauncher<Intent>
    var users:ArrayList<User> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    }
}