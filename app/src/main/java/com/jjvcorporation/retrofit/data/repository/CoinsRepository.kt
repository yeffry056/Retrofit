package com.jjvcorporation.retrofit.data.repository

import com.jjvcorporation.retrofit.data.CoinApi
import com.jjvcorporation.retrofit.data.remote.RetrofitInstance
import com.jjvcorporation.retrofit.data.remote.dto.Coin
import com.jjvcorporation.retrofit.di.AppModule
import com.jjvcorporation.retrofit.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val api: CoinApi
) {
    fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val coins = api.getCoins() //descarga las monedas de internet, se supone quedemora algo

            emit(Resource.Success(coins)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun postCoin(coin: Coin): Response<Coin>{
        //api.postCoin(coin)
        return RetrofitInstance.api.postCoin(coin)//.postCoin(coin)
    }

}