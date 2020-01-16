package ru.android.ufstask.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.android.ufstask.helper.ResultWrapper
import ru.android.ufstask.models.NewsRes

/*
 * Created by yasina on 2020-01-14
*/
interface ApiInterface {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): NewsRes

}
