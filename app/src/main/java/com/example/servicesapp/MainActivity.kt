package com.example.servicesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            val intent =Intent(this,MyService::class.java)
            startService(intent)
        }

        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            val intent =Intent(this,MyService::class.java)
            stopService(intent)
        }

    }
}