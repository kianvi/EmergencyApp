package com.example.emergencyapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun emergency (view: View) {

        val emergencyIntent = Intent(this, emergency::class.java)

        login_button.setOnClickListener {
            lateinit var sharedPreferences: SharedPreferences
            sharedPreferences=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
            val context = this
            val db = DBEmergency(context)
            val password = db.getPassword(editTextPhone.text.toString())
            if(editTextTextPassword.text.toString() == password && editTextTextPassword.text.isNotEmpty() && editTextPhone.text.isNotEmpty()) {
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("phone", editTextPhone.text.toString())
                editor.apply()
                Toast.makeText(context, "Signed", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Failed phone or password", Toast.LENGTH_SHORT).show()
            }

            startActivity(emergencyIntent)
        }

    }

    fun onClick(view: View) {
        val signupIntent = Intent(this, signup::class.java)

        textView2.setOnClickListener {
            startActivity(signupIntent)
        }
        textView4.setOnClickListener {
            startActivity(signupIntent)
        }

    }


}