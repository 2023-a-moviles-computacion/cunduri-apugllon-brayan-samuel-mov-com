import java.io.*
import java.util.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalQueries
import java.util.*
import kotlin.collections.ArrayList
fun main() {
    var arrayPlaylist : ArrayList<Playlist> = arrayListOf<Playlist>()

    leerArchivo(arrayPlaylist)
    do{
        println("FREE MUSIC PLAYER\n")
        println("1. Listar Playlist\n" +
                "2. Crear Playlist\n" +
                "3. Editar Playlist\n" +
                "4. Borrar Playlist\n"+
                "5. Ingresar a Playlist\n"+
                "6. Salir")
        var opcion= readLine()!!
        when (opcion) {
            "1" -> {
                printPlayLt(arrayPlaylist)
            }
            "2" -> {
                var continuar = "y"
                do {
                    crearPlaylist(arrayPlaylist)
                    printPlayLt(arrayPlaylist)
                    println("Seguir insertando? y/n")
                    continuar = readLine()!!
                } while (continuar == "y")
            }
            "3" -> {
                modificarPlaylist(arrayPlaylist)
                printPlayLt(arrayPlaylist)
            }
            "4" -> {
                borrarPlaylist(arrayPlaylist)
                printPlayLt(arrayPlaylist)
            }
            "5" -> {
                printPlayLt(arrayPlaylist)
                println("Seleccione el ID de la Playlist: ")
                var idPlt = readLine()!!.toInt()
                do {
                    printnombrePlaylist(arrayPlaylist,idPlt)
                    println(
                        "1. Listar Canciones\n" +
                                "2. Insertar cancion\n" +
                                "3. Modificar datos de la cancion\n" +
                                "4. Borrar cancion\n" +
                                "5. Salir"
                    )
                    var opcionSong = readLine()!!
                    when (opcionSong) {
                        "1" -> {
                            printCancion(arrayPlaylist, idPlt)
                        }
                        "2" -> {
                            var continuar = "y"
                            do {
                                insertarCancion(arrayPlaylist, idPlt)
                                printCancion(arrayPlaylist, idPlt)
                                println("Seguir insertando? y/n")
                                continuar = readLine()!!
                            }while (continuar=="y")
                        }
                        "3" -> {
                            println("Digite el ID de la cancion: ")
                            var idSong = readLine()!!.toInt()
                            modificarCancion(arrayPlaylist, idPlt,idSong)
                            printCancion(arrayPlaylist, idPlt)
                        }
                        "4" -> {
                            println("Digite el ID de la cancion: ")
                            var idSong = readLine()!!.toInt()
                            borrarCancion(arrayPlaylist, idPlt,idSong)
                            printCancion(arrayPlaylist, idPlt)
                        }
                        else -> {
                            println("Adios")
                            break
                        }
                    }
                } while (opcionSong!="5")
            }
            else -> {
                println("Adios")
                break
            }
        }
    }while (opcion!="6")
    saveFile(arrayPlaylist)
}

fun crearPlaylist(arrayPlaylist:ArrayList<Playlist>){ // insertar playlist
    println("Ingrese el nombre de la Playlist")
    var nombre=readLine()!!
    println("Ingrese la descripcion")
    var descripcion=readLine()!!


    if(arrayPlaylist.isEmpty()){
        arrayPlaylist.add(Playlist(1,nombre,
            descripcion, arrayListOf<Cancion>()) )
    }else{
        arrayPlaylist.add(Playlist(arrayPlaylist.get(arrayPlaylist.size - 1).idPlaylist +1,nombre,
            descripcion, arrayListOf<Cancion>()) )
    }
}

fun borrarPlaylist(arrayPLaylist:ArrayList<Playlist>){ //
    println("Eliminar Playlist : digite el ID: ")
    var idPborrar= readLine()!!.toInt()
    for (element in arrayPLaylist) {
        if (element.idPlaylist==idPborrar) {
            println("el index:"+arrayPLaylist.indexOf(element))
            arrayPLaylist.removeAt(arrayPLaylist.indexOf(element))
            break
        }
    }
}

fun modificarPlaylist(arrayPlaylist: ArrayList<Playlist>){ // Update
    println("Digite el ID de la Playlist")
    var idPmodificar= readLine()!!.toInt()
    for (element in arrayPlaylist) {
        if (element.idPlaylist==idPmodificar) {
            println("Ingrese el nombre de la Playlist")
            var nombre=readLine()!!
            println("Ingrese la descripcion")
            var descripcion=readLine()!!


            println("el index:"+arrayPlaylist.indexOf(element))
            arrayPlaylist.set(arrayPlaylist.indexOf(element),Playlist(element.idPlaylist,nombre,
                descripcion,element.songs))
        }
    }
}

fun printPlayLt(arrayPlaylist: ArrayList<Playlist>){
    println("ID\tNombre Playlist\tDescripcion\n" )

    for (playt in arrayPlaylist)
    {
        println(""+playt.idPlaylist+"\t"+playt.nombre+"\t"+playt.descripcion+"\t"+
                playt.songs)
    }
}

fun printnombrePlaylist(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int){
    var idPlayLt= idPlaylst
    for (element in arrayPlaylist) {
        if (element.idPlaylist == idPlayLt) {
            println("PLAYLIST " + element.nombre.uppercase())
        }
    }
}

fun printCancion(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int){ //printMed
    var idPlayLt= idPlaylst
    for (element in arrayPlaylist) {
        if (element.idPlaylist==idPlayLt) {
            println("PLAYLIST " + element.nombre.uppercase())
            println("\nIDP\tID\tNombre\tArtista\tGenero\n")
            for (song in element.songs)
            {
                println(""+song.idPlyt+"\t"+song.idCancion+"\t"+song.nombreCancion+"\t"+
                        song.artista+"\t"+song.genero)
            }
        }}
}
fun insertarCancion(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int){
    println("Insertar cancion")
    var idPlayLt= idPlaylst
    for (element in arrayPlaylist) {
        if (element.idPlaylist==idPlayLt) {
            println("Ingrese el nombre de la cancion: ")
            var nombreCancion = readLine()!!
            println("Ingrese el nombre del artista: ")
            var artista = readLine()!!
            println("Ingrese el genero: ")
            var genero = readLine()!!

            if(element.songs.isEmpty()) {
                element.songs.add(Cancion(idPlayLt, 1, nombreCancion, artista, genero))
            }else
            {
                element.songs.add(Cancion(idPlayLt, element.songs.get(element.songs.size - 1).idCancion +1, nombreCancion, artista, genero))
            }
            println("\nIDP\tID\tNombre\tArtista\tGenero\n")
            for (song in element.songs)
            {
                println(""+song.idPlyt+"\t"+song.idCancion+"\t"+song.nombreCancion+"\t"+
                        song.artista+"\t"+song.genero)
            }
        }}
}


fun modificarCancion(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int,idSong:Int)
{
    println("Modificar Canción")
    var idPlayLt = idPlaylst
    for (element in arrayPlaylist) {
        if (element.idPlaylist==idPlayLt) {
            for (song in element.songs){
                if (song.idCancion==idSong){
                    println("Ingrese el nombre de la cancion: ")
                    var nombreCancion = readLine()!!
                    println("Ingrese el nombre del artista: ")
                    var artista = readLine()!!
                    println("Ingrese el genero: ")
                    var genero = readLine()!!
                    element.songs.set(element.songs.indexOf(song),Cancion(idPlayLt, idSong , nombreCancion, artista, genero))
                }
            }

        }}
}

fun borrarCancion(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int,idSong:Int)
{
    println("Borrar cancion")
    var idPlaylst= idSong
    for (element in arrayPlaylist) {
        if (element.idPlaylist==idPlaylst) {
            for (song in element.songs){
                if (song.idCancion==idSong){
                    element.songs.removeAt(element.songs.indexOf(song))
                    break
                }
            }
        }}
}

fun saveFile(arrayPlaylist: ArrayList<Playlist>){
    val nombre="out/Archivos/Playlists.txt"
    val archivo=File(nombre)
    if (!archivo.exists()){
        archivo.createNewFile()
    }
    val fileWriter = FileWriter(archivo)
    val bufferedWriter = BufferedWriter(fileWriter)
    var salida:String=""
    for(element in arrayPlaylist){
        salida+=("${element.toString()}")
    }
    println(salida)
    bufferedWriter.write("$salida")
    bufferedWriter.close()
}
fun leerArchivo(arrayPlaylist: ArrayList<Playlist>){
    val fr = FileReader("out/Archivos/Playlists.txt")
    var fileText = ""
    var i: Int
    while (fr.read().also { i = it } != -1) {
        fileText += i.toChar()
    }
    var count = 0
    for (i in 0 until fileText.length) {
        val letter: Char = fileText[i]
        if (letter == '\n') ++count
    }
    var elemntosArchive=ArrayList<String>(fileText.split("\n"))
    for(i in 0..count-1){
        var (idPlaylist, nombre, descripcion, songs)=elemntosArchive[i].split("|")
        var canciones=ArrayList<String>((songs.substring(1,songs.length-1)).split(","))
        var rolitas=ArrayList<Cancion>() // rolitas = canciones
        if(canciones[0].length>0){
            for(cancion in canciones) {
                var (idPlyt, idCancion, nombreCan, artistaCan, generoCan) = cancion.split(";")
                idPlyt=idPlyt.replace("\\s".toRegex(),"")
                var rolas =Cancion(idPlaylist.toInt(), idCancion.toInt(), nombreCan, artistaCan, generoCan)
                rolitas.add(rolas)
            }
        } else {
            println("")
        }
        var playlist= Playlist(idPlaylist.toInt(),nombre, descripcion, rolitas)
        arrayPlaylist.add(playlist)
    }
}

data class Playlist(

    var idPlaylist:Int,
    var nombre:String,
    var descripcion:String,
    var songs: ArrayList<Cancion>  = arrayListOf<Cancion>()
) {
    override fun toString(): String {
        return "$idPlaylist|$nombre|$descripcion|${songs.toString()}\n"
        /* return "{\n" +
                 "\t\"id\":$id,\n" +
                 "\t\"nombre\":\"$nombre\",\n" +
                 "\t\"direccion\":\"$direccion\",\n" +
                 "\t\"numeroTelefonico\":\"$numeroTelefonico\",\n" +
                 "\t\"enOperacion\":$enOperacion,\n" +
                 "\t\"meds\":\n" +
                 "\t${meds.toString()},\n" +
                 "\t},\n"*/
    }
}

data class Cancion(

    var idPlyt:Int,
    var idCancion: Int,
    var nombreCancion:String,
    var artista: String,
    var genero:String,
    //var anioRelease:localDate, // anio de lanzamiento
) {
    override fun toString(): String {
        /* return "\t\t{\n" +
                  "\t\t\"idFarmacia\":$idFarmacia,\n" +
                  "\t\t\"idMedicamento\":$idMedicamento,\n" +
                  "\t\t\"nombre\":\"$nombre\",\n" +
                  "\t\t\"precio\":$precio,\n" +
                  "\t\t\"cantidad\":$cantidad,\n" +
                  "\t\t\"fechaCaducidad\":\"$fechaCaducidad\"\n" +
                  "\t\t}\n"*/
        return "$idPlyt;$idCancion;$nombreCancion;$artista;$genero"
    }
}

