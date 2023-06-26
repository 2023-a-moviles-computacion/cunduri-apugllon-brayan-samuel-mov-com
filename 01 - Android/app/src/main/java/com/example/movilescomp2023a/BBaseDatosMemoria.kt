package com.example.movilescomp2023a

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador= arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Samuel","a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Brayan","n@n.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Hernan","b@b.com")
                )
        }
    }
}