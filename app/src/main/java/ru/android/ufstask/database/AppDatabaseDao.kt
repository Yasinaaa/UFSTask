package ru.android.ufstask.database

import androidx.room.*
import ru.android.ufstask.models.Article


/*
 * Created by yasina on 2020-01-14
*/
@Dao
interface AppDatabaseDao {

    @Query("SELECT * FROM article_table ORDER BY publishedAt DESC")
    fun getAllNews(): MutableList<Article>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(data: MutableList<Article>)

}