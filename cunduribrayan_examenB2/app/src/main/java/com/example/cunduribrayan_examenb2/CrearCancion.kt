package com.example.cunduribrayan_examenb2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearCancion : AppCompatActivity() {

    var playlistSeleccionado = Playlist("", "", "", 0, "", 0)
    val db = Firebase.firestore
    val playlists = db.collection("Playlists")
    val canciones = db.collection("Canciones")
    var idCancionSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cancion)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        playlistSeleccionado = intent.getParcelableExtra<Playlist>("posicionPlaylist")!!
        val playlistSubColeccion = playlists.document("${playlistSeleccionado.idPlaylist}")
            .collection("Canciones")


        var txtNombre = findViewById<TextInputEditText>(R.id.txtIn_nombreC_crear)
        var txtArtista = findViewById<TextInputEditText>(R.id.txtIn_artistaC_crear)
        var txtDuracion = findViewById<TextInputEditText>(R.id.txtIn_duracionC_crear)
        var txtGenero= findViewById<TextInputEditText>(R.id.txtIn_generoC_crear)
        var txtAnioRelease = findViewById<TextInputEditText>(R.id.txtIn_anioC_crear)

        Log.i("posPlaylist", "${playlistSeleccionado.idPlaylist}")

        var btnAddCancion= findViewById<Button>(R.id.btn_crear_cancion)
        btnAddCancion.setOnClickListener {
            var cancion = hashMapOf(
                "idPlaylist" to playlistSeleccionado.idPlaylist,
                "nombre" to txtNombre.text.toString(),
                "artista" to txtArtista.text.toString(),
                "duracion" to txtDuracion.text.toString(),
                "genero" to txtGenero.text.toString(),
                "anioRealease" to txtAnioRelease.text.toString()
            )

            playlistSubColeccion.add(cancion).addOnSuccessListener {
                Toast.makeText(this, "Cancion registrada exitosamente", Toast.LENGTH_SHORT).show();
                Log.i("Crear-Cancion", "Con exito")
            }.addOnFailureListener {
                Log.i("Crear-Cancion", "Fallido")
            }

            val intentAddSucces = Intent(this, InicioCancion::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarCancion = findViewById<Button>(R.id.btn_cancelar_cancion_crear)
        btnCancelarCancion.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posPlaylist", playlistSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}