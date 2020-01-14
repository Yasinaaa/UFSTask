package ru.android.ufstask

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.android.ufstask.database.DatabaseModule
import ru.android.ufstask.repositories.RootRepository
import ru.android.ufstask.ui.charters_list_screen.ChartersListScreenViewModel

/*
 * Created by yasina on 2020-01-14
*/
class App: Application(){

    private var appModule = module {
        single { RootRepository }
    }

    private var viewModels = module {
        viewModel { ChartersListScreenViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        DatabaseModule.context = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    viewModels
                )
            )
        }
    }
}