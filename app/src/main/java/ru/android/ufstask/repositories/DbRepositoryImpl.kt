package ru.android.ufstask.repositories

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import kotlinx.coroutines.*
import ru.android.ufstask.database.AppDatabaseDao
import ru.android.ufstask.database.DatabaseModule
import ru.android.ufstask.models.*

/*
 * Created by yasina on 2020-01-16
*/
class DbRepositoryImpl: Repository.DbRepository {

    private val appDatabaseDao: AppDatabaseDao = DatabaseModule.getInstanse()

    override fun insertNews(news: MutableList<Article>, complete: () -> Unit) {
        appDatabaseDao.insertArticles(news) //todo check is success
    }

    override fun getAllNews(): MutableList<Article> {
        return appDatabaseDao.getAllNews()
    }
}