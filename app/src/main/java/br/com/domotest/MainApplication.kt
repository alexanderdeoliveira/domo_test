package br.com.domotest

import android.app.Application
import br.com.domotest.di.appModule
import br.com.domotest.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                appModule,
                databaseModule
            )
        }
    }
}