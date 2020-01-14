package ru.android.ufstask.common.extensions

//import ru.android.ufstask.AppConfig
//import ru.android.ufstask.common.model.HousesTabs
import ru.android.ufstask.data.local.entities.House
import ru.android.ufstask.data.local.entities.HouseItem
import ru.android.ufstask.data.remote.res.HouseRes

/*
 * Created by yasina on 2020-01-14
*/
fun HouseRes.transformToHouse() = House(
    id = "namae",
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
    id = "namae",
    name = this.name,
    region = this.region
)
//fun HouseRes.getShortName() = when (AppConfig.NEED_HOUSES.indexOf(name)) {
//    0 -> HousesTabs.STARK.tabName
//    1 -> HousesTabs.LANNISTER.tabName
//    2 -> HousesTabs.TARGARYEN.tabName
//    3 -> HousesTabs.BARATHEON.tabName
//    4 -> HousesTabs.GREYJOY.tabName
//    5 -> HousesTabs.MARTEL.tabName
//    6 -> HousesTabs.TYRELL.tabName
//    else -> ""
//}
