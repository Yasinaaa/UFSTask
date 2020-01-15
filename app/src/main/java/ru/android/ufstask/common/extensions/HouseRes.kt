package ru.android.ufstask.common.extensions


import ru.android.ufstask.data.local.entities.House
import ru.android.ufstask.data.local.entities.HouseItem
import ru.android.ufstask.data.remote.res.HouseRes

/*
 * Created by yasina on 2020-01-14
*/
fun House.transformToHouseRes() = HouseRes(
    name = this.name,
    region = this.region,
    coatOfArms = this.coatOfArms,
    words = this.words,
    titles = this.titles,
    seats = this.seats,
    currentLord = this.currentLord,
    heir = this.heir,
    overlord = this.overlord,
    founded = this.founded,
    founder = this.founder,
    diedOut = this.diedOut,
    ancestralWeapons = this.ancestralWeapons
)
fun HouseRes.transformToHouse() = House(
    id = 0L,
    name = this.name,
    region = this.region,
    coatOfArms = this.coatOfArms,
    words = this.words,
    titles = this.titles,
    seats = this.seats,
    currentLord = this.currentLord,
    heir = this.heir,
    overlord = this.overlord,
    founded = this.founded,
    founder = this.founder,
    diedOut = this.diedOut,
    ancestralWeapons = this.ancestralWeapons
)
fun HouseRes.transformToHouseItem() = HouseItem (
    id = 0L,
    name = this.name,
    region = this.region
)

