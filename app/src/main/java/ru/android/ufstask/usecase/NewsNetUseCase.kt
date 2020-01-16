package ru.android.ufstask.usecase

import kotlinx.coroutines.*
import ru.android.ufstask.helper.ResultWrapper
import ru.android.ufstask.models.NewsRes
import ru.android.ufstask.repositories.NetRepositoryImpl
import java.util.concurrent.TimeUnit

/*
 * Created by yasina on 2020-01-14
*/
class NewsNetUseCase(val repository: NetRepositoryImpl){

    private val coroutineJob = Job()
    private val localScope = CoroutineScope(Dispatchers.Main + coroutineJob)

    fun getNews(country: String, apiKey: String, result: (houses: ResultWrapper<NewsRes>) -> Unit){
        localScope.launch{
            val task = async(Dispatchers.IO) {
                return@async repository.getCurrentNews(country, apiKey)
            }
            result(task.await())
        }
    }

//    fun startCoroutineTimer(delayMillis: Long = 0, repeatMillis: Long = 0, action: () -> Unit) = GlobalScope.launch {
//        delay(delayMillis)
//        if (repeatMillis > 0) {
//            while (true) {
//                action()
//                delay(repeatMillis)
//            }
//        } else {
//            action()
//        }
//    }

}