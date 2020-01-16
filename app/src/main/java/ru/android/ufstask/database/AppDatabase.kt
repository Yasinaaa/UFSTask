package ru.android.ufstask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.android.ufstask.models.Article
import ru.android.ufstask.models.convertToDate
import java.text.SimpleDateFormat
import java.util.*

/*
 * Created by yasina on 2020-01-14
*/
@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TimestampConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val appDatabaseDao: AppDatabaseDao
}

class TimestampConverter {

    @TypeConverter
    fun dateToTimestamp(date: Date?): String? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return format.format(date!!)
    }

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return value.convertToDate()
    }

}
