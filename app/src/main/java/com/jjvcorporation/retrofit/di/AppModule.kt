package com.jjvcorporation.retrofit.di



import com.jjvcorporation.retrofit.data.CoinApi
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

    @Provides
    @Singleton
    fun provideCoinRepository(coinApi: CoinApi): CoinsRepository {
        return CoinsRepository(coinApi)
    }
}