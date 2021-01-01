package com.wahyu.core.di

import androidx.room.Room
import com.wahyu.core.data.source.FootballRepository
import com.wahyu.core.data.source.local.LocalDataSource
import com.wahyu.core.data.source.local.database.FootballDatabase
import com.wahyu.core.data.source.remote.RemoteDataSource
import com.wahyu.core.data.network.ApiService
import com.wahyu.core.domain.repository.IFootballRepository
import com.wahyu.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object CoreModule {
    val databaseModule = module {
        factory { get<FootballDatabase>().footballDao() }
        single {
            Room.databaseBuilder(
                androidContext(),
                FootballDatabase::class.java, "Football.db"
            ).fallbackToDestructiveMigration().build()
        }
    }

    val networkModule = module {
        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api-football-v1.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }

    val repositoryModule = module {
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        factory { AppExecutors() }
        single<IFootballRepository> {
            FootballRepository(
                get(),
                get(),
                get()
            )
        }
    }
}