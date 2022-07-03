package com.jjvcorporation.retrofit.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coin(
    val monedaId: Int =0,
    val descripcion : String? ="",
    val valor : Double ,
    val imageUrl : String? =""
)

/*
val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val rank: Int = 0,
    val is_new: Boolean = false,
    val is_active: Boolean = false,
    val type: String = ""
 */
