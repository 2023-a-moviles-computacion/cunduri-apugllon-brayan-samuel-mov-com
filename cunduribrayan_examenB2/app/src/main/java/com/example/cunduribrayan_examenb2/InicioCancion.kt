package com.example.cunduribrayan_examenb2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioCancion : AppCompatActivity() {

    var playlistSeleccionado=Playlist("","","",0,"",0)
    val db = Firebase.firestore
    val playlists = db.collection("Playlists")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<Cancion>?=null
    var cancionSeleccionado:Cancion? = Cancion("","", "", "", 0, "", "")


    var resultAnadirCancion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                playlistSeleccionado = intent.getParcelableExtra<Playlist>("PosPlaylist")!!
            }
        }

    }

    var resultEditarCancion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                playlistSeleccionado = intent.getParcelableExtra<Playlist>("PosPlaylist")!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_cancion)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        playlistSeleccionado = intent.getParcelableExtra<Playlist>("PosPlaylist")!!
        listViewCanciones()
        val txtNombrePlaylist=findViewById<TextView>(R.id.tv_nombreP_C)
        txtNombrePlaylist.setText("Playlist: "+playlistSeleccionado.nombrePlaylist)

        val btnCrearCancion = findViewById<Button>(R.id.btn_crear_cancion2)
        btnCrearCancion.setOnClickListener {
            abrirActividadAddCancion(CrearCancion::class.java)
        }

        val btnVolverCancion = findViewById<Button>(R.id.btn_volver_cancion)
        btnVolverCancion.setOnClickListener {
            val intentAtrasCancion = Intent(this, InicioPlaylist::class.java)
            startActivity(intentAtrasCancion)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_cancion, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idItemSeleccionado = info.position
        Log.i("context-menu", "ID Cancion ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        cancionSeleccionado = adaptador!!.getItem(idItemSeleccionado)
        return when (item.itemId) {
            R.id.mi_editarCancion -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarCancion(EditarCancion::class.java)
                return true
            }
            R.id.mi_eliminarCancion -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                val playlistSubColeccion= playlists.document("${playlistSeleccionado.idPlaylist}")
                    .collection("Canciones")
                    .document("${cancionSeleccionado!!.idCancion}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-Cancion","Con exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-Cancion","Fallido")
                    }
                listViewCanciones()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun listViewCanciones() {
        val playlistSubColeccion= playlists.document("${playlistSeleccionado.idPlaylist}")
            .collection("Canciones")
            .whereEqualTo("idPlaylist","${playlistSeleccionado.idPlaylist}")

        val canciones_lv = findViewById<ListView>(R.id.lv_canciones_lista)
        playlistSubColeccion.get().addOnSuccessListener { result ->
            var listaCanciones = arrayListOf<Cancion>()
            for(document in result){
                listaCanciones.add(
                    Cancion(
                        document.id.toString(),
                        document.data.get("idPlaylist").toString(),
                        document.data.get("nombreCancion").toString(),
                        document.data.get("artista").toString(),
                        document.data.get("duracion").toString().toInt(),
                        document.data.get("genero").toString(),
                        document.data.get("anioRelease").toString()
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                listaCanciones
            )
            canciones_lv.adapter=adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(canciones_lv)
        }
    }

    fun abrirActividadEditarCancion(
        clase: Class<*>
    ) {
        val intentEditarCancion = Intent(this, clase)
        intentEditarCancion.putExtra("cancion", cancionSeleccionado)
        intentEditarCancion.putExtra("posicionPlaylisteditar",playlistSeleccionado)
        resultEditarCancion.launch(intentEditarCancion)
    }

    fun abrirActividadAddCancion(
        clase: Class<*>
    ) {
        val intentAddNewCancion = Intent(this, clase)
        intentAddNewCancion.putExtra("posicionPlaylist", playlistSeleccionado)
        resultAnadirCancion.launch(intentAddNewCancion)
    }

}