package ru.android.ufstask.ui.charters_list_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.android.ufstask.data.local.entities.CharacterItem
import ru.android.ufstask.data.remote.res.HouseRes
import ru.android.ufstask.repositories.RootRepository

class ChartersListScreenViewModel(private val repository: RootRepository) : ViewModel() {

    //private var data = MutableLiveData<Map<String, List<CharacterItem>>>()
    private var data = MutableLiveData<List<HouseRes>>()

    private val query = MutableLiveData<String>("")

    init {
      //  data.value = mapOf()
        data.value = mutableListOf()
//        repository.findCharactersByHouseName("Stark") { characters ->
//            data.value = data.value?.plus("Stark" to characters)
//        }

//        HousesTabs.values().forEach { houseTab ->
//            repository.findCharactersByHouseName(houseTab.tabName) { characters ->
//                data.value = data.value?.plus(houseTab.tabName to characters)
//            }
//        }
        repository.getAllHouses { list ->
            data.value = data.value!!.plus(list)
//            data.value = data.value.plus(list)
        }
    }

    fun getData(): LiveData<List<HouseRes>> {
        val result = MediatorLiveData<List<HouseRes>>()
        val filterF = {
            result.value = data.value
                //if (query.value.isNullOrEmpty()) data.value ?: mutableListOf()
//            else data.value!!.map { list ->
//                list.key to list.value.filter { items ->
//                    items.name.contains(query.value!!, true)
//
//                }

        }

        result.addSource(data) { filterF.invoke() }
        result.addSource(query) { filterF.invoke() }
        return result
    }

//    fun getData2() : LiveData<Map<String, List<CharacterItem>>> {
//        val result = MediatorLiveData<Map<String, List<CharacterItem>>>()
//        val filterF = {
//            result.value = if (query.value.isNullOrEmpty()) data.value ?: mapOf()
//            else data.value!!.map { list ->
//                list.key to list.value.filter { items ->
//                    items.name.contains(query.value!!, true)
//
//                }
//            }.toMap()
//        }
//        result.addSource(data) { filterF.invoke() }
//        result.addSource(query) { filterF.invoke() }
//        return result
//    }
//
//
//    fun handleSearchQuery(queryStr: String?) {
//        query.value = queryStr.orEmpty()
//    }
}