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

class EditarCancion : AppCompatActivity() {

    var playlistSeleccionado = Playlist("", "", "", 0, "", 0)
    var cancionSeleccionado = Cancion("","", "", "", 0, "", "")
    val db = Firebase.firestore
    val playlists = db.collection("Playlists")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cancion)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        playlistSeleccionado = intent.getParcelableExtra<Playlist>("posicionPlaylisteditar")!!
        cancionSeleccionado = intent.getParcelableExtra<Cancion>("cancion")!!

        val txtNombreC = findViewById<TextInputEditText>(R.id.txtIn_nombreC_editar)
        val txtartistaC = findViewById<TextInputEditText>(R.id.txtIn_artistaC_editar)
        val txtduracionC = findViewById<TextInputEditText>(R.id.txtIn_duracionC_editar)
        val txtgeneroC = findViewById<TextInputEditText>(R.id.txtIn_generoC_editar)
        val txtanioreleaseC = findViewById<TextInputEditText>(R.id.txtIn_anioC_editar)

                txtNombreC.setText(cancionSeleccionado.nombreCancion)
                txtartistaC.setText(cancionSeleccionado.artista)
                txtduracionC.setText(cancionSeleccionado.duracion.toString())
                txtgeneroC.setText(cancionSeleccionado.genero)
                txtanioreleaseC.setText(cancionSeleccionado.anioRelease)


        val btnEditarCancion = findViewById<Button>(R.id.btn_editar_cancion)
        btnEditarCancion.setOnClickListener {
            playlists.document("${playlistSeleccionado.idPlaylist}")
                .collection("Canciones")
                .document("${cancionSeleccionado.idPlaylist_C}")
                .update(
                    "nombreCancion" , txtNombreC.text.toString(),
                    "artista" , txtartistaC.text.toString(),
                    "duracion" , txtduracionC.text.toString(),
                    "genero" , txtgeneroC.text.toString(),
                    "anioRelease" , txtanioreleaseC.text.toString()
                )

            Toast.makeText(this,"Cancion actualizada exitosamente", Toast.LENGTH_SHORT).show()
            val intentEditSucces = Intent(this, InicioCancion::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_cancion_editar)
        btnCancelar.setOnClickListener{
            respuesta()
        }

    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionPlaylisteditar",playlistSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}