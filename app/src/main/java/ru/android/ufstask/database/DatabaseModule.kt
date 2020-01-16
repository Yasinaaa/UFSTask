package ru.android.ufstask.database

import android.content.Context
import androidx.room.Room

/*
 * Created by yasina on 2020-01-14
*/
object DatabaseModule {

    lateinit var context: Context

    fun getInstanse() = providesDao(provideDatabase(context))

    private fun provideDatabase(context: Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, "app_database")
        .build()

    private fun providesDao(database: AppDatabase) = database.appDatabaseDao

}