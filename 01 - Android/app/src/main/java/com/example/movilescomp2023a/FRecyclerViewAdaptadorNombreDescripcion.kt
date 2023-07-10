package com.example.movilescomp2023a

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreDescripcion(
    private val contexto: FRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreDescripcion.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }

    // Setear el layout que vamos a usar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

    }
    // Setear datos iteracion al iniciar el adaptador
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    // tamano del arreglo
    override fun getItemCount(): Int {

    }


}