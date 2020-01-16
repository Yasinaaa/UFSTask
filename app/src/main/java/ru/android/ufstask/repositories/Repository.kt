package ru.android.ufstask.repositories

import ru.android.ufstask.helper.ResultWrapper
import ru.android.ufstask.models.Article
import ru.android.ufstask.models.NewsRes

/*
 * Created by yasina on 2020-01-16
*/
object Repository {

    interface DbRepository{
        fun insertNews(news: MutableList<Article>, complete: () -> Unit)
        fun getAllNews(): MutableList<Article>
    }

    interface NetRepository{
        suspend fun getCurrentNews(country: String, apiKey: String): ResultWrapper<NewsRes>
    }
}