package ru.android.ufstask.data.remote.res

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
 * Created by yasina on 2020-01-14
*/
@Parcelize
data class HouseRes(
    val url: String,
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    val titles: List<String> = listOf(),
    val seats: List<String> = listOf(),
    val currentLord: String,
    val heir: String,
    val overlord: String,
    val founded: String,
    val founder: String,
    val diedOut: String,
    val ancestralWeapons: List<String> = listOf(),
    val cadetBranches: List<String> = listOf(),
    val swornMembers: List<String> = listOf()
) : Parcelable