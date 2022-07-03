package com.jjvcorporation.retrofit.data

import com.jjvcorporation.retrofit.data.remote.dto.Coin
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface CoinApi {

    @GET("/Coins")///v1/coins
    suspend fun getCoins(): List<Coin>

    @GET("/Coins/{coinId}")///v1/coins/
    suspend fun getCoin(@Path("coinId") coinId: String): Coin

   // @Headers("Content-Type: application/json")

    @POST("/Coins")
    suspend fun postCoin(@Body coin: Coin): Response<Coin>
    //http://jsonplaceholder.typicode.com/posts
}