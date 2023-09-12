package com.example.cunduribrayan_examenb2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class InicioPlaylist : AppCompatActivity() {
    val db = Firebase.firestore
    val playlists = db.collection("Playlists")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<Playlist>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_playlist)
        Log.i("ciclo-vida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        listarPlaylists()
        val btnAnadirPlaylist = findViewById<Button>(R.id.btn_crear_nueva_playlist)
        btnAnadirPlaylist.setOnClickListener {
            val intentAddPlaylist = Intent(this, CrearPlaylist::class.java)
            startActivity(intentAddPlaylist)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var playlistSeleccionado:Playlist = adaptador!!.getItem(idItemSeleccionado)!!

        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${playlistSeleccionado.idPlaylist}")
                val abrirEditarPlaylist = Intent(this, EditarPlaylist::class.java)
                abrirEditarPlaylist.putExtra("PosPlaylist",playlistSeleccionado)
                startActivity(abrirEditarPlaylist)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                playlists.document("${playlistSeleccionado.idPlaylist}").delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-Playlist", "Exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-Playlist","Fallido")
                    }
                listarPlaylists()
                return true
            }

            R.id.mi_canciones -> {
                Log.i("context-menu", "Canciones: ${idItemSeleccionado}")
                val abrirInicioCanciones = Intent(this, InicioCancion::class.java)
                abrirInicioCanciones.putExtra("PosPlaylist",playlistSeleccionado)
                startActivity(abrirInicioCanciones)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun listarPlaylists(){
        val lv_playlists = findViewById<ListView>(R.id.lv_playlists_lista)
        playlists.get().addOnSuccessListener{ result ->
            var playlistLista = arrayListOf<Playlist>()
            for (document in result) {
                playlistLista.add(
                    Playlist(
                        document.id.toString(),
                        document.get("nombrePlaylist").toString(),
                        document.get("descripcionPlaylist").toString(),
                        document.get("anioCreacion").toString().toInt(),
                        document.get("autorPlaylist").toString(),
                        document.get("numCanciones").toString().toInt(),
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                playlistLista
            )
            lv_playlists.adapter = adaptador
            adaptador!!.notifyDataSetChanged()
            registerForContextMenu(lv_playlists)

        }.addOnFailureListener{
            Log.i("Error", "Creacion de lista de playlists fallida")
        }
    }

}