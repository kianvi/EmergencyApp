package com.example.emergencyapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.profile.*
import android.database.sqlite.SQLiteDatabase
import java.time.LocalDateTime

class profile : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
        lateinit var sharedPreferences: SharedPreferences
        sharedPreferences =getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val context = this
        val db = DBEmergency(context)

        //textView13.setText(db.getServiceCall(sharedPreferences.getString("phone", "").toString()))
        //textView14.setText(db.getAddressCall(sharedPreferences.getString("phone", "").toString()))
        //textView15.setText(db.getDateCall(sharedPreferences.getString("phone", "").toString()))
        textView14.setText(db.getAddress(sharedPreferences.getString("phone", "").toString()))
        textView15.setText(LocalDateTime.now().toString())
        phonetext.setText(sharedPreferences.getString("phone", "").toString())
        nametext.setText(db.getName(sharedPreferences.getString("phone", "").toString()))
        addresstext.setText(db.getAddress(sharedPreferences.getString("phone", "").toString()))
    }
    fun emergency (view: View) {

        val emergencyIntent = Intent(this, emergency::class.java)

        logo_image.setOnClickListener {
            startActivity(emergencyIntent)
        }

    }

    fun signup (view: View) {

        val signupIntent = Intent(this, signup::class.java)

        textView9.setOnClickListener {
            startActivity(signupIntent)
        }

    }



}