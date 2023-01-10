package com.example.doup_app

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ChatAdapter(
    val c:Activity,
    val msg: ArrayList<Msg>,
    val login:String
):ArrayAdapter<Msg>(c,R.layout.chat,msg) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=c.layoutInflater.inflate(R.layout.chat,null,true)
        val tv=view.findViewById<TextView>(R.id.tv)
        val pesan=msg[position]
        tv.text=pesan.toString()
        if (login==pesan.tipe){
            tv.textAlignment=View.TEXT_ALIGNMENT_TEXT_END
        }
        return view
    }
}