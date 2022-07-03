package com.jjvcorporation.retrofit.data.remote

import com.jjvcorporation.retrofit.data.CoinApi
import com.jjvcorporation.retrofit.data.remote.dto.Coin
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

//import retrofit2.converter
//.GsonConverterFactory

object RetrofitInstance {


    /*@Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }*/

        val moshi: Moshi = Moshi.Builder().build()


        private val retrofit by lazy {

            Retrofit.Builder()
                .baseUrl("http://JeffersonJ.somee.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }



    val api: CoinApi by lazy {
        retrofit.create(CoinApi::class.java)
    }

   /* val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }*/
}