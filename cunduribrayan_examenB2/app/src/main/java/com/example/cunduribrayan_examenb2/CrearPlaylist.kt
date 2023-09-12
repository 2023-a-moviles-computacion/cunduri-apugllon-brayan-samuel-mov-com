package com.example.cunduribrayan_examenb2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CrearPlaylist : AppCompatActivity() {

    val db = Firebase.firestore
    val playlists = db.collection("Playlists")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_playlist)
    }

    override fun onStart() {
        super.onStart()

        var txtInNombreP = findViewById<TextInputEditText>(R.id.txtIn_nombreP_editar2)
        var txtInDescripcion = findViewById<TextInputEditText>(R.id.txtIn_descripcionP_editar)
        var txtInAnioCreacion = findViewById<TextInputEditText>(R.id.txtIn_anioP_editar)
        var txtInAutorP = findViewById<TextInputEditText>(R.id.txtIn_autorP_editar)
        var txtInNumCancion = findViewById<TextInputEditText>(R.id.txtIn_numCP_editar)

        var btnCrearPlaylist = findViewById<Button>(R.id.btn_guardar_cambios)
        btnCrearPlaylist.setOnClickListener {
            var playlist = hashMapOf(
                "nombrePlaylist" to txtInNombreP.text.toString(),
                "descripcionPlaylist" to txtInDescripcion.text.toString(),
                "anioCreacion" to txtInAnioCreacion.text.toString(),
                "autorPlaylist" to txtInAutorP.text.toString(),
                "numCanciones" to txtInNumCancion.text.toString()
            )

            playlists.add(playlist).addOnSuccessListener {
                txtInNombreP.text!!.clear()
                txtInDescripcion.text!!.clear()
                txtInAnioCreacion.text!!.clear()
                txtInAutorP.text!!.clear()
                txtInNumCancion.text!!.clear()
                Toast.makeText(this,"Playlist registrado con exito", Toast.LENGTH_SHORT).show();
                Log.i("Crear-Playlist","Success")
            }.addOnSuccessListener {
                Log.i("Crear-Playlist","Failed")
            }


            val intentAddSucces = Intent(this, InicioPlaylist::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarPlaylist = findViewById<Button>(R.id.btn_cancelar_editar)
        btnCancelarPlaylist.setOnClickListener {
            val intentAddCancel = Intent(this, InicioPlaylist::class.java)
            startActivity(intentAddCancel)
        }
    }

}