package com.richard.vinyx.core.di

import androidx.room.Room
import com.richard.vinyx.core.data.DetailRepository
import com.richard.vinyx.core.data.GameRepository
import com.richard.vinyx.core.data.source.local.LocalDataSource
import com.richard.vinyx.core.data.source.local.room.DetailDatabase
import com.richard.vinyx.core.data.source.local.room.GameDatabase
import com.richard.vinyx.core.data.source.remote.RemoteDataSource
import com.richard.vinyx.core.data.source.remote.network.ApiService
import com.richard.vinyx.core.domain.repository.IDetailRepository
import com.richard.vinyx.core.domain.repository.IGameRepository
import com.richard.vinyx.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<DetailDatabase>().detailDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            DetailDatabase::class.java, "Detail.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("key", "5ec93d04943546c8bffb3aac95529e05")
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGameRepository> { GameRepository(get(), get(), get()) }
    single<IDetailRepository> { DetailRepository(get(), get()) }
}