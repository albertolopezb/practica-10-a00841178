package mx.tec.avisos.domain

data class Aviso(
    val id: Int,
    val titulo: String,
    val cuerpo: String,
    val autor: String,
    val creadoEn: String,
    val imagenUrl: String? = null
)