package com.example.cunduribrayan_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnIniciar = findViewById<Button>(R.id.btn_iniciarSesion1)
       iniciarDatos()

        btnIniciar.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        val btnSalir = findViewById<Button>(R.id.btn_salir)

        btnSalir.setOnClickListener{
            finish()
        }
    }
     fun iniciarDatos(){
         val db = Firebase.firestore
         val categorias = db.collection("Categorias_Proyecto")

         var categoria=hashMapOf(
             "Nombre" to "Trabajo",
             "Descripcion" to "Tareas de mi trabajo")
         var categoria2=hashMapOf(
             "Nombre" to "Casa",
             "Descripcion" to "Tareas de mi hogar")
         categorias.add(categoria)
         categorias.add(categoria2)

     }
}