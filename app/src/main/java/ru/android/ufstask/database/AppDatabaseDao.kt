package ru.android.ufstask.database

import androidx.room.*
import ru.android.ufstask.data.local.entities.House

/*
 * Created by yasina on 2020-01-14
*/
@Dao
interface AppDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: House)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<House>)

    @Query("SELECT * FROM house_table WHERE id = :key")
    fun get(key: String): House?

    @Query("DELETE FROM house_table")
    fun clear()

    @Query("SELECT * FROM house_table")
    fun getAll(): MutableList<House>
}