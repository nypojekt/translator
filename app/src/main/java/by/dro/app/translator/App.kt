package by.dro.app.translator

import android.app.Application
import by.dro.app.translator.di.*
import org.koin.android.ext.android.startKoin

open class App: Application() {

    private val appComponent = listOf(networkModule, viewModelModule, repositoryModule)

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appComponent)
    }
}