package ru.android.ufstask.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.android.ufstask.data.remote.res.HouseRes

/*
 * Created by yasina on 2020-01-14
*/
interface ApiInterface {

    @GET("houses")
    suspend fun needPage(@Query("page") name: Int): MutableList<HouseRes>

}
