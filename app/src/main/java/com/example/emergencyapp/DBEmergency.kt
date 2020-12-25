package com.example.emergencyapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.time.format.DateTimeFormatter


val DATABASENAME = "Emergency_DB"
val TABLENAME1 = "Users"
val TABLENAME2 = "Calls"
val COL_ID = "id"
val COL_FIRSTNAME = "firstname"
val COL_SECONDNAME = "secondname"
val COL_PHONE = "phone"
val COL_PASSWORD="password"
val COL_EMAIL = "email"
val COL_ADDRESS = "address"
val COL_DATE = "date"
val COL_SERVICE = "service"

 class DBEmergency(var context: Context):SQLiteOpenHelper(context, DATABASENAME,null,
1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable1 = "CREATE TABLE " + TABLENAME1 + " ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_FIRSTNAME+" text, "+
                COL_SECONDNAME+" text, "+ COL_PHONE+" text, "+ COL_PASSWORD+" text, "+ COL_EMAIL+" text, "+ COL_ADDRESS+" text)"
        db?.execSQL(createTable1)

        val createTable2 = "CREATE TABLE Calls ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_PHONE+" text, "+
                COL_ADDRESS+" text, "+ COL_DATE+" text, "+ COL_SERVICE+" text)"
        db?.execSQL(createTable2)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun insertData(user: User){
        val db = this.writableDatabase
        val Values = ContentValues()
        Values.put(COL_FIRSTNAME, user.firstname)
        Values.put(COL_SECONDNAME, user.secondname)
        Values.put(COL_PHONE, user.phone)
        Values.put(COL_PASSWORD, user.password)
        Values.put(COL_EMAIL, user.email)
        Values.put(COL_ADDRESS, user.address)

        val result = db.insert(TABLENAME1, null, Values)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

     fun insertDataCall(call: Call){
         val db = this.writableDatabase
         val Values1 = ContentValues()

         Values1.put(COL_PHONE, call.phone)
         Values1.put(COL_ADDRESS, call.address)
         Values1.put(COL_DATE, call.date)
         Values1.put(COL_SERVICE, call.service)

         val result = db.insert("Calls", null, Values1)
         if (result == (0).toLong()) {
             Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
         }
         else {
             Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
         }
     }

    fun getAddress(phone: String): String{
        val db = this.readableDatabase
        var str = ""
        var query = "select * from "+ TABLENAME1+" where "+COL_PHONE+" = \'" + phone+"\'"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            str=result.getString(result.getColumnIndex(COL_ADDRESS))
        }
        result.close()
        return str
    }
     fun getPassword(phone: String): String {
         val db = this.readableDatabase
         var str = ""
         val query = "Select * from " + TABLENAME1 + " where " + COL_PHONE + " = \'" + phone+"\'"
         val result = db.rawQuery(query, null)
         if (result.moveToFirst()) {
             str=result.getString(result.getColumnIndex(COL_PASSWORD))
         }
         result.close()
         return str
     }
     fun getName(phone: String): String {
         val db = this.readableDatabase
         var str = ""
         val query = "Select * from " + TABLENAME1 + " where " + COL_PHONE + " = \'" + phone+"\'"
         val result = db.rawQuery(query, null)
         if (result.moveToFirst()) {
             str=result.getString(result.getColumnIndex(COL_FIRSTNAME)) + " " + result.getString(result.getColumnIndex(
                 COL_SECONDNAME))
         }
         result.close()
         return str
     }
     fun getServiceCall(phone: String): String{
         val db = this.readableDatabase
         var str =""
         var query = "select top(1) * from" + TABLENAME2 + "where "+COL_PHONE+" = \'" + phone+"\'"
         val result = db.rawQuery(query,null)
         if(result.moveToFirst()) {
             str = result.getString(result.getColumnIndex(COL_SERVICE))
         }
         result.close()
         return str
     }

     fun getDateCall(phone: String): String{
         val db = this.readableDatabase
         var str =""
         var query = "select top(1) * from" + TABLENAME2 + "where "+COL_PHONE+" = \'" + phone+"\'"
         val result = db.rawQuery(query,null)
         if(result.moveToFirst()) {
             str = result.getString(result.getColumnIndex(COL_DATE))
         }
         result.close()
         return str
     }

     fun getAddressCall(phone: String): String{
         val db = this.readableDatabase
         var str =""
         var query = "select top(1) * from" + TABLENAME2 + "where "+COL_PHONE+" = \'" + phone+"\'"
         val result = db.rawQuery(query,null)
         if(result.moveToFirst()) {
             str = result.getString(result.getColumnIndex(COL_ADDRESS))
         }
         result.close()
         return str
     }


     fun updateAddress(address: String, phone: String) {

         val db = this.writableDatabase
         var query = "Update " + TABLENAME2 + " set address = \'" + address +"\' where phone = \'" + phone+"\'"

         db.execSQL(query)
         /*val Values = ContentValues()
         Values.put(COL_ADDRESS, address)
         db.update(TABLENAME,  Values, "phone =? " , arrayOf(phone))
         db.close()*/

     }

    }


