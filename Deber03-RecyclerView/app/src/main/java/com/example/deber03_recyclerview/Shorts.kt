package com.example.deber03_recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Shorts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shorts)

        val listaShorts = arrayListOf<ShortsPiratas>()
        listaShorts.add(ShortsPiratas("¿Podríamos ver el pasado?","Date un Top",
            "https://yt3.googleusercontent.com/ZWoKS4daKo5HD175bRZ3Vh9DZnyIGhxY3l2EH3sAAcpzg1peR1x1xAOcV_-uCSAncc3Uv0udPw=s176-c-k-c0x00ffffff-no-rj",
            "https://i.ytimg.com/vi/qY9q1DxU8Yw/oar2.jpg?sqp=-oaymwEaCJUDENAFSFXyq4qpAwwIARUAAIhCcAHAAQY=&rs=AOn4CLBIqZJUSrriEMBRHGEZmqn0IcrvmQ",
        "https://youtube.com/shorts/qY9q1DxU8Yw?si=1nEmIqeI_MZNMJrx"))

        listaShorts.add(ShortsPiratas("Cómo será el fin del universo","CdeCiencia",
            "https://yt3.googleusercontent.com/ytc/AOPolaRnQqS8_bLVkHhbP_HwRg2yRDy3J58crKUI8asPpw=s176-c-k-c0x00ffffff-no-rj",
            "https://i.ytimg.com/vi/8oLuFH0b2GE/oar2.jpg?sqp=-oaymwEaCJUDENAFSFXyq4qpAwwIARUAAIhCcAHAAQY=&rs=AOn4CLCA4wN1NIbaWzwK5GrdoIMjvrRPUw",
            "https://youtube.com/shorts/8oLuFH0b2GE?si=1Cz3zYYt9Q0DiiGN"))

        val recyclerView = findViewById<RecyclerView>(R.id.rv_videos_short)
        inicializarRV(listaShorts,recyclerView)

        val navmenu = findViewById<BottomNavigationView>(R.id.nav_menu_yt1)
        navmenu.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.act_principal -> {
                    irActividad(MainActivity::class.java)
                    true
                }
                R.id.act_shorts -> {
                    irActividad(Shorts::class.java)
                    true
                }
                else -> false
            }
        }
    }

    fun inicializarRV(
        lista:ArrayList<ShortsPiratas>,
        recyclerView:RecyclerView
    ){
        val adaptador = AdaptadorShorts(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}