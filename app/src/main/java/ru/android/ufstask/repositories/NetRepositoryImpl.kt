package ru.android.ufstask.repositories

import kotlinx.coroutines.*
import ru.android.ufstask.network.ApiInterface
import ru.android.ufstask.helper.ResultWrapper
import ru.android.ufstask.helper.safeApiCall
import ru.android.ufstask.models.*
import ru.android.ufstask.network.NetworkModule

/*
 * Created by yasina on 2020-01-15
*/
class NetRepositoryImpl: Repository.NetRepository {

    private val apiInterface: ApiInterface = NetworkModule.INSTANSE
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getCurrentNews(country: String, apiKey: String): ResultWrapper<NewsRes> {
        return safeApiCall(dispatcher) { apiInterface.getNews(country, apiKey) }
    }


}
