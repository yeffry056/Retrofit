package com.jjvcorporation.retrofit.data

import com.jjvcorporation.retrofit.data.remote.dto.Coin
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("/Coins")///v1/coins
    suspend fun getCoins(): List<Coin>

    @GET("/Coins/{coinId}")///v1/coins/
    suspend fun getCoin(@Path("coinId") coinId: String): Coin

}