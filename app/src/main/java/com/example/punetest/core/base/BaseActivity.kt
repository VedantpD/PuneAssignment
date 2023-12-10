package com.example.punetest.core.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.punetest.R

open class BaseActivity : AppCompatActivity() {
    protected open fun showToast(message : String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    // TO DO
    protected open fun String.log(){
        Log.d("maini", this)
    }

    protected fun nextActivity(destinationActivity: Class<*>) {
        val intent = Intent(this, destinationActivity)
        startActivity(intent)
    }
}