package com.angelme.pokedex.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.angelme.pokedex.remote.PokedexService
import com.angelme.pokedex.repository.DefaultRepository
import com.angelme.pokedex.repository.PokemonRepository
import com.angelme.pokedex.repository.remote.RemoteDataSource
import com.angelme.pokedex.repository.remote.RetrofitDataSource
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    const val BASE_URL = "https://pokeapi.co/api/v2/"
    private val cacheSize = (5 * 1024 * 1024).toLong()

    @Provides
    @Singleton
    fun hasNetwork(app: Application): Boolean {
        val connectivityManager =
            app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        return activeNetwork != null
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(app: Application): OkHttpClient {
        val cache = Cache(app.cacheDir, cacheSize)
        return OkHttpClient
            .Builder()
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(app)) {
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                } else {
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                }
                chain.proceed(request)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(PokedexService::class.java)

    @Singleton
    @Provides
    fun provideFirebaseDB(): FirebaseDatabase = Firebase.database

    @Singleton
    @Provides
    fun provideFirebaseRef(
        firebaseDatabase: FirebaseDatabase,
        firebaseAuth: FirebaseAuth
    ): DatabaseReference =
        firebaseDatabase.getReference(firebaseAuth.currentUser?.uid ?: "default")
}
