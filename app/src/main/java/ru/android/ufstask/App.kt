package ru.android.ufstask

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.android.ufstask.database.DatabaseModule
import ru.android.ufstask.repositories.DbRepositoryImpl
import ru.android.ufstask.repositories.NetRepositoryImpl
import ru.android.ufstask.ui.NewsViewModel
import ru.android.ufstask.usecase.NewsDbUseCase
import ru.android.ufstask.usecase.NewsNetUseCase

/*
 * Created by yasina on 2020-01-14
*/
class App: Application(){

    private var viewModels = module {

        viewModel { NewsViewModel(
            NewsNetUseCase(NetRepositoryImpl()),
            NewsDbUseCase(DbRepositoryImpl()),
            applicationContext)
        }
    }

    override fun onCreate() {
        super.onCreate()

        DatabaseModule.context = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    viewModels
                )
            )
        }
    }
}