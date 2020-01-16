package ru.android.ufstask.usecase

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import kotlinx.coroutines.*
import ru.android.ufstask.models.*
import ru.android.ufstask.repositories.DbRepositoryImpl

/*
 * Created by yasina on 2020-01-14
*/
class NewsDbUseCase(val repository: DbRepositoryImpl){

    private val coroutineJob = Job()
    private val localScope = CoroutineScope(Dispatchers.Main + coroutineJob)

    fun insertNews(news: MutableList<ArticleRes>, complete: (Boolean) -> Unit) {
        localScope.launch {
            try {
                val task = async(Dispatchers.IO) {

                    repository.insertNews(news.transformToArticleList()){
                        return@insertNews
                    }
                    return@async true
                }
                task.await()
                complete.invoke(true) //todo
            } catch (error: Exception) {
                Log.i(Constraints.TAG, error.toString())
            }
        }
    }

    fun getAllNews(result: (houses: MutableList<ArticleItem>) -> Unit) {
        localScope.launch {
            try {
                val task = async(Dispatchers.IO) {
                    val currentList = mutableListOf<ArticleItem>() //todo
                    val houses = repository.getAllNews()
                    houses.forEach { house ->
                        currentList.add(house.transformToArticle())
                    }
                    return@async currentList
                }
                val taskResult = task.await()
                result(taskResult)
            } catch (error: Exception) {
                Log.i(Constraints.TAG, error.toString())
            }
        }
    }
}