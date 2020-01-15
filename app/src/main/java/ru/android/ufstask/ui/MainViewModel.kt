package ru.android.ufstask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.android.ufstask.data.remote.res.HouseRes
import ru.android.ufstask.repositories.RootRepository

/*
 * Created by yasina on 2020-01-14
*/
class MainViewModel(private val repository: RootRepository) : ViewModel() {

    private var data = MutableLiveData<MutableList<HouseRes>>()

    init {
        data.value = mutableListOf()

        repository.getAllHousesFromDb {
            data.value!!.addAll(it)
            data.postValue(data.value)
        }

        repository.getAllHouses(0) { list, _ ->
            data.value!!.addAll(list)
            data.postValue(data.value)
            repository.insertHouses(list) {}
        }
    }

    fun getData(): LiveData<MutableList<HouseRes>> {
        val result = MediatorLiveData<MutableList<HouseRes>>()
        val filterF = {
            result.value = data.value
        }
        result.addSource(data) { filterF.invoke() }
        return result
    }


}