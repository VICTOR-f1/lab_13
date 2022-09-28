package com.example.trylistjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var button2: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar!!.hide()
        button = findViewById(R.id.button)
        button.setOnClickListener{
            val intent:Intent = Intent(this, AddbookActivity::class.java)
            startActivity(intent)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, ShowBookActivity::class.java)
            startActivity(intent)
        }
    }
}