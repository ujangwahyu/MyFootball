package com.wahyu.myfootball

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.wahyu.core.di.CoreModule
import com.wahyu.myfootball.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    CoreModule.databaseModule,
                    CoreModule.networkModule,
                    CoreModule.repositoryModule,
                    AppModule.useCaseModule,
                    AppModule.viewModelModule
                )
            )
        }
    }
}