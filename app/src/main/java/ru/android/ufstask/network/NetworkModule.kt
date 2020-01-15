package ru.android.ufstask.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
 * Created by yasina on 2020-01-14
*/
object NetworkModule {

    const val BASE_URL = "https://www.anapioficeandfire.com/api/"
    val INSTANSE : ApiInterface = provideApiInterface(provideOkHttpClient(provideInterceptor()))

    fun provideApiInterface(okHttpClient: OkHttpClient): ApiInterface =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

    fun provideInterceptor() = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient
        .Builder()
        .readTimeout(7, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}