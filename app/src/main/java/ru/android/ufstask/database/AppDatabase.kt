package ru.android.ufstask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.android.ufstask.data.local.entities.*

/*
 * Created by yasina on 2020-01-14
*/
@Database(
    entities = [House::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val appDatabaseDao: AppDatabaseDao
}
