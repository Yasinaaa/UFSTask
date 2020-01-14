package ru.android.ufstask.database

import androidx.room.*
import ru.android.ufstask.data.local.entities.House
import ru.android.ufstask.data.local.entities.Character

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
    fun getAll(): List<House>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCharacters(data: List<Character>)

    @Query("SELECT * FROM character_table WHERE id = :key")
    fun getCharacter(key: String): Character?

    @Query("DELETE FROM character_table")
    fun clearCharacters()

    @Query("SELECT * FROM character_table WHERE name = :key")
    fun getCharacterByName(key: String): Character?

    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): List<Character>

    @Query("SELECT * FROM character_table ORDER BY id DESC LIMIT 1")
    fun getLastCharacter(): Character?

    @Query("SELECT * FROM character_table WHERE houseId LIKE :houseId")
    fun getCharactersFromHouse(houseId: String): List<Character>
}