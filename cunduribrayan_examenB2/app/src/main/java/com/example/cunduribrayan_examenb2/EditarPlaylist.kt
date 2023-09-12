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

class EditarPlaylist : AppCompatActivity() {

    var playlistSeleccionado = Playlist("", "", "", 0, "", 0)
    val db = Firebase.firestore
    val playlists = db.collection("Playlists")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_editar_playlist)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        playlistSeleccionado = intent.getParcelableExtra<Playlist>("PosPlaylist")!!

        val editarNombreP = findViewById<TextInputEditText>(R.id.txtIn_nombreP_editar2)
        val editarDescripcion = findViewById<TextInputEditText>(R.id.txtIn_descripcionP_editar2)
        val editarAnioCreacion = findViewById<TextInputEditText>(R.id.txtIn_anioP_editar2)
        val editarAutorPlaylist = findViewById<TextInputEditText>(R.id.txtIn_autorP_editar2)
        val editarNumC = findViewById<TextInputEditText>(R.id.txtIn_numCP_editar2)

        editarNombreP.setText(playlistSeleccionado.nombrePlaylist)
        editarDescripcion.setText(playlistSeleccionado.descripcionPlaylist.toString())
        editarAnioCreacion.setText(playlistSeleccionado.anioCreacion.toString())
        editarAutorPlaylist.setText(playlistSeleccionado.autorPlaylist.toString())
        editarNumC.setText(playlistSeleccionado.numCanciones.toString())


        val btnGuardarCambios = findViewById<Button>(R.id.btn_guardar_cambios2)
        btnGuardarCambios.setOnClickListener {
            playlists.document("${playlistSeleccionado.idPlaylist}").update(
                "nombrePlaylist" , editarNombreP.text.toString(),
                "descripcionPlaylist" , editarDescripcion.text.toString(),
                "anioCreacion" , editarAnioCreacion.text.toString(),
                "autorPlaylist" , editarAutorPlaylist.text.toString(),
                "numCanciones" , editarNumC.text.toString()
            )
            Toast.makeText(this,"Playlist actualizado exitosamente", Toast.LENGTH_SHORT).show()

            val intentEditSucces = Intent(this, InicioPlaylist::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_cancelar_editar2)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, InicioPlaylist::class.java)
            startActivity(intentEditCancel)
        }

    }

}