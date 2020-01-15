package ru.android.ufstask.data.local.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

/*
 * Created by yasina on 2020-01-14
*/
@Entity(tableName = "house_table")
data class House(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "region")
    val region: String,

    @ColumnInfo(name = "coatOfArms")
    val coatOfArms: String,

    @ColumnInfo(name = "words")
    val words: String,

    @ColumnInfo(name = "seats")
    val titles: List<String>,

    @ColumnInfo(name = "titles")
    val seats: List<String>,

    @ColumnInfo(name = "currentLord")
    val currentLord: String, //rel

    @ColumnInfo(name = "heir")
    val heir: String, //rel

    @ColumnInfo(name = "overlord")
    val overlord: String,

    @ColumnInfo(name = "founded")
    val founded: String,

    @ColumnInfo(name = "founder")
    val founder: String, //rel

    @ColumnInfo(name = "diedOut")
    val diedOut: String,

    @ColumnInfo(name = "ancestralWeapons")
    val ancestralWeapons: List<String>
)

@Parcelize
data class HouseItem(
    val id: Long,
    val region: String,
    val name: String
) : Parcelable

class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> = value.split(";")

    @TypeConverter
    fun fromArrayList(list: List<String>) = list.joinToString(";")
}