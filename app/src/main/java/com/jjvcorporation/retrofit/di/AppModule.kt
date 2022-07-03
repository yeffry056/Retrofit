package com.jjvcorporation.retrofit.di



import com.jjvcorporation.retrofit.data.CoinApi
import com.jjvcorporation.retrofit.data.remote.dto.Coin
import com.jjvcorporation.retrofit.data.repository.CoinsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun prodiveCoinApi(moshi: Moshi): CoinApi {
        return Retrofit.Builder()
            .baseUrl("http://JeffersonJ.somee.com")//"https://api.coinpaprika.com"
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CoinApi::class.java)
    }
    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("http://JeffersonJ.somee.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }



    val api: CoinApi by lazy {
        retrofit.create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(coinApi: CoinApi): CoinsRepository {
        return CoinsRepository(coinApi)
    }

    /*val api: CoinApi by lazy {
        retrofit.create(CoinApi::class.java)
    }*/


}