import java.util.*;

fun main(args: Array<String>) {
    println("Hello my World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    // Tipos de variables

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

    // ARRREGLOS
    // Tipos de Arreglos

    // Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Arreglo Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //OPERADORES -> Sirven para los arreglos estaticos y dinamicos
    // FOR EACH -> Unit
    // Iterar un arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }

    arregloDinamico.forEach{ println(it)} // it (en ingles eso) significa el elemento

    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map{valorActual:Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map {it+15}

    //FILTER -Z FILTRAR EL ARREGLO
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter{ valorActual: Int->
            val mayoresACinco: Boolean = valorActual > 5 // Expresion condicion
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter{it<=5}
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    // OR -> ANY (Alguno cumple?)
    // AND -> ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5) // retorna un boolean
        }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all {  valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // false

    //REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5
    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            //println("acumulado: ${acumulado}")
            return@reduce (acumulado + valorActual) // -> Logica negocio
        }
    println("Valor acumulado: ${respuestaReduce}") // 78
}



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




