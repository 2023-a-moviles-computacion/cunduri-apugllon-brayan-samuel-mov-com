package com.example.movilescomp2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IFirestore : AppCompatActivity() {

    var query: Query? = null
    val arreglo: ArrayList<ICities> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirestore)
        // Configurando el list view
        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Como se va a mostrar
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        //Botones
        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener { crearDatosPrueba() }
        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener {
            consultarConOrderBy(adaptador)
        }
    }

    fun consultarConOrderBy(
        adaptador: ArrayAdapter<ICities>
    ) {
        val db = Firebase.firestore
        val citiesRefUnico = db
            .collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico

            .orderBy("population", Query.Direction.ASCENDING)
            .get() // obtenemos la peticion
            .addOnSuccessListener {
                for (ciudad in it) { // it => eso (lo que llegue)
                    ciudad.id
                    anadirAArregloCiudad()
                }
            }
            .addOnFailureListener{
                //Errores
            }
    }

    fun limpiarArreglo() {
        arreglo.clear()
    }

    fun anadirAArregloCiudad(){}

    fun crearDatosPrueba(){
        //colecciones deben tener nombres unicos
        val db = Firebase.firestore
        // asi llenamos cada una de las ciudades
        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal"),
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal"),
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast"),
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu"),
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei"),
        )
        cities.document("BJ").set(data5)
    }


}
