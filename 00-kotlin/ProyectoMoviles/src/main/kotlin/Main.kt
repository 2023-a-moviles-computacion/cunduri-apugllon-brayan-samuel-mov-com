import java.util.*;

fun main(args: Array<String>) {
    println("Hello my World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    // Tipos de varianles

    // INMUTALES (NO SE REASIGNAN "*")
    val inmutable :String = "Brayan";
    // inmutable = "Samuel";

    // Mutables (Re asignar)
    var mutable: String = "Samuel";
    mutable = "Brayan";

    //val = var

    // Duck typing
    var ejemploVariable = "Samuel Cunduri";
    //val edadEjemplo: Int ) 12;
    ejemploVariable.trim() // el trim quita los espacion
    // ejemploVariable = edadEjemplo;

    // Varibale primitiva
    val nombreProfesor: String = "Adrian Equez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C' // comilla simples en char, comillas dobles en string
    val mayorEdad: Boolean = true
    // Clases Java
    val fechaNacimiento: Date = Date()

    // SWITCH, en este el when es equivalente al switch
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        "S" ->{
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    val coqueteo = if (estadoCivilWhen == "S") "S1" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00, 12.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //named parameters, parametros nombrados
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) // parametros nombrados

    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)
}

// void ->Unit
fun imprimirNombre(nombre: String): Unit{
    println("Nombre : ${nombre}")
}


fun calcularSueldo(
    sueldo: Double, // Requerido
    tasa: Double = 12.00, // Opcional (defecto)
    bonoEspecial: Double? = null, // Opcion null -> nullable
): Double{
    // Int -> Int? , (nullable )con esto indico que es null en algun punto
    // String -> String? (nulable)
    // Date -> Date? (nullable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){// Bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros(// constructor PRIMARIO
    // Ejemplo:
    // uno: Int, (Parametro(sin modificador de acceso))
    // public var uno: Int, // Propiedad Publica Clase numeros.uno
    // var uno: Int. // Propiedad de la clase (por defecto es PUBLIC)
    // public var uno: Int,
protected val numeroUno: Int, // Propiedad de la clase protected numros.numeroUno
protected val numeroDos: Int, // Propiedad de la clase protected numros.numeroDos
){
    // var cedula: string = "" (public es por defecto)
    // private valorCalculado: Int = 0 (private)
    init {
        this.numeroUno; this.numeroDos; // this es opcional
        numeroUno; numeroDos; // sin el "this", es lo mismo
        println("Inicializando")
    }
}

class Suma(// Constructor Primario Suma
uno : Int, // Parametro
dos: Int // Parametro
) : Numeros (uno, dos){ // <- Constructor del padre
    init { // Bloque constructor primario
        this.numeroUno; numeroUno;
        this.numeroDos; numeroDos;

    }

    constructor(// segundo constructor
        uno: Int?, // parametros
        dos: Int // parametros
    ) : this ( // llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    ){// si necesitamos bloque de codigo lo usamos
        numeroUno;
    }

    constructor(// tercer constructor
        uno: Int, // parametros
        dos: Int? // parametros
    ) : this( // llamada constructor primario
        uno,
        if (dos == null) 0 else uno
    )// si necesitamos bloque de codigo lo usamos

    constructor( // cuarto constructor
        uno: Int?, //parametros
        dos: Int? //parametros
    ) : this(// llamada constructor primario
        if(uno == null) 0 else uno,
        if(dos == null) 0 else uno
    )

    public fun sumar(): Int{// public por defecto, o usar private o protected
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object{ // Atributos y metodos "Compartidos"
        // entre las instancias
        // equivalnete al static
        // se comparte entre las distintas instancias
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int{
            return num*num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }


}





// ARRREGLOS
// Tipos de Arreglos

// Arreglo Estatico
val arregloEstatico: Array<Int> = arrayOf<Int>()


