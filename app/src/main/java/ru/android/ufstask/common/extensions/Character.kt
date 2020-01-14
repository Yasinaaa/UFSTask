package ru.android.ufstask.common.extensions

import ru.android.ufstask.data.local.entities.CharacterItem
import ru.android.ufstask.data.local.entities.Character
import ru.android.ufstask.data.local.entities.House
import ru.android.ufstask.data.local.entities.HouseItem

/*
 * Created by yasina on 2020-01-14
*/
fun Character.transformToCharacterItem() = CharacterItem (
    id = this.id,
    name = this.name,
    titles = this.titles,
    aliases = this.aliases,
    house = this.houseId
)
