package com.example.movilescomp2023a

class BBaseDatosMemoria {
    companion object{
        val arrgloBEntrenador= arrayListOf<BEntrenador>()
        init {
            arrgloBEntrenador
                .add(
                    BEntrenador(1, "Samuel","a@a.com")
                )
            arrgloBEntrenador
                .add(
                    BEntrenador(2, "Brayan","n@n.com")
                )
            arrgloBEntrenador
                .add(
                    BEntrenador(3, "Hernan","b@b.com")
                )
        }
    }
}