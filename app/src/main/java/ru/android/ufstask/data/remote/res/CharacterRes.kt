package ru.android.ufstask.data.remote.res

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
 * Created by yasina on 2020-01-14
*/
@Parcelize
data class CharacterRes(
    val url: String,
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val titles: List<String> = listOf(),
    val aliases: List<String> = listOf(),
    val father: String,
    val mother: String,
    val spouse: String,
    val allegiances: List<String> = listOf(),
    val books: List<String> = listOf(),
    val povBooks: List<String> = listOf(),
    val tvSeries: List<String> = listOf(),
    val playedBy: List<String> = listOf(),
    var houseId: String = ""
): Parcelable