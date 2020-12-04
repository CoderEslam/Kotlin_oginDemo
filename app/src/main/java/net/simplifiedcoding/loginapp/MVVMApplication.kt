package net.simplifiedcoding.loginapp

import android.app.Application
import net.simplifiedcoding.loginapp.data.db.AppDatabase
import net.simplifiedcoding.loginapp.data.network.MyApi
import net.simplifiedcoding.loginapp.data.network.NetworkConnectionInterceptor
import net.simplifiedcoding.loginapp.data.preferences.PreferenceProvider
import net.simplifiedcoding.loginapp.data.repositories.UserRepository
import net.simplifiedcoding.loginapp.ui.auth.AuthViewModelFactory
import net.simplifiedcoding.loginapp.ui.home.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }



    }

}