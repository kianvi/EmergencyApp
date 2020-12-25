package com.example.emergencyapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.location.*

class location : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location)
    }
    fun emergency (view: View) {

        val emergencyIntent = Intent(this, emergency::class.java)

        login_button.setOnClickListener {
            lateinit var sharedPreferences: SharedPreferences

            val context = this
            val db = DBEmergency(context)
            sharedPreferences =getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

            val newaddress = "Manasa"
            db.updateAddress(newaddress, sharedPreferences.getString("phone", "").toString())
            startActivity(emergencyIntent)
        }

    }
}