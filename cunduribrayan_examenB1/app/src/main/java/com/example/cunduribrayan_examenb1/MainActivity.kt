package com.example.cunduribrayan_examenb1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciar = findViewById<Button>(R.id.btn_iniciar)
        btnIniciar.setOnClickListener{
            val intent = Intent(this, InicioPlaylist::class.java)
            startActivity(intent)
        }

    }
}