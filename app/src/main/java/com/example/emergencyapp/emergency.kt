package com.example.emergencyapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.emergency.*
import kotlinx.android.synthetic.main.signup.*
import java.time.LocalDateTime

class emergency : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var sharedPreferences: SharedPreferences
        sharedPreferences =getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        setContentView(R.layout.emergency)
        val context = this
        val db = DBEmergency(context)
        textView5.setText(db.getAddress(sharedPreferences.getString("phone", "").toString()))
        //textView5.setText(sharedPreferences.getString("phone", "").toString())


        button4.setOnClickListener {
            val call = Call(sharedPreferences.getString("phone", "").toString(), db.getAddress(sharedPreferences.getString("phone", "").toString()), LocalDateTime.now().toString(),  "Police")
            db.insertDataCall(call)

            val intent102 = Intent(Intent.ACTION_CALL)
            intent102.data = Uri.parse("tel:102" )
            startActivity(intent102)

        }
        button5.setOnClickListener {
            val call = Call(sharedPreferences.getString("phone", "").toString(), db.getAddress(sharedPreferences.getString("phone", "").toString()), LocalDateTime.now().toString(),  "Ambulance")
            db.insertDataCall(call)

            val intent103 = Intent(Intent.ACTION_CALL)
            intent103.data = Uri.parse("tel:103" )
            startActivity(intent103)

        }
    }

    fun profile (view: View) {

        val profileIntent = Intent(this, profile::class.java)

        profile_image.setOnClickListener {
            startActivity(profileIntent)
        }

    }

    fun location (view: View) {

        val locationIntent = Intent(this, location::class.java)

        textView5.setOnClickListener {
            startActivity(locationIntent)
        }

    }


}