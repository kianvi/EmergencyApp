package com.example.emergencyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.signup.*

class signup : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
    }

    fun login (view: View) {

        val loginIntent = Intent(this, MainActivity::class.java)

        next_button.setOnClickListener {
            val context = this
            val db=DBEmergency(context)
            val user = User(firstname.text.toString(), secondname.text.toString(), phone.text.toString(), email.text.toString(), password.text.toString(), "Address")
            db.insertData(user)
            startActivity(loginIntent)
        }

    }
}