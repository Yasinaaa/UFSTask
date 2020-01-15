package ru.android.ufstask.repositories

import android.util.Log
import androidx.constraintlayout.widget.Constraints.TAG
import kotlinx.coroutines.*
import ru.android.ufstask.common.extensions.*
import ru.android.ufstask.network.ApiInterface
import ru.android.ufstask.data.remote.res.HouseRes
import ru.android.ufstask.database.AppDatabaseDao
import ru.android.ufstask.database.DatabaseModule
import ru.android.ufstask.network.NetworkModule

/*
 * Created by yasina on 2020-01-14
*/
object RootRepository {

    private val apiInterface: ApiInterface = NetworkModule.INSTANSE
    private val appDatabaseDao: AppDatabaseDao = DatabaseModule.getInstanse()
    private val coroutineJob = Job()
    private val localScope = CoroutineScope(Dispatchers.Main + coroutineJob)


    /**
     * Получение данных о всех домах
     * @param result - колбек содержащий в себе список данных о домах
     */
    fun getAllHouses(page: Int, result: (houses: MutableList<HouseRes>, pageNum: Int) -> Unit) {

        localScope.launch {
            try {
                val pageNumber = page + 1
                val data = apiInterface.needPage(pageNumber)
                if (data.isNullOrEmpty()) {
                    return@launch
                }else {
                    val task = async(Dispatchers.IO) {
                        return@async data
                    }
                    result(task.await(), pageNumber)
                    getAllHouses(pageNumber, result)
                }
            } catch (error: Exception) {
                Log.i(TAG, error.toString())
            }
        }
    }

    fun getAllHousesFromDb(result: (houses: MutableList<HouseRes>) -> Unit) {

        localScope.launch {
            try {
                val task = async(Dispatchers.IO) {
                    val currentList = mutableListOf<HouseRes>()
                    val houses = appDatabaseDao.getAll()

                    houses.forEach { house ->
                        currentList.add(house.transformToHouseRes())

                    }
                    return@async currentList
                }
                val taskResult = task.await()
                result(taskResult)
            } catch (error: Exception) {
                Log.i(TAG, error.toString())
            }
        }
    }

    /**
     * Получение данных о требуемых домах по их полным именам (например фильтрация всех домов)
     * @param houseNames - массив полных названий домов (смотри AppConfig)
     * @param result - колбек содержащий в себе список данных о домах
     */
//    fun getNeedHouses(vararg houseNames: String, result: (houses: List<HouseRes>) -> Unit) {
//
//        localScope.launch {
//            try {
//                val task = async(Dispatchers.IO) {
//                    val currentList = mutableListOf<HouseRes>()
//
//                    houseNames.forEach { houseName ->
//
//                        currentList.addAll(apiInterface.needHouse(houseName))
//
//                    }
//                    return@async currentList
//                }
//                val taskResult = task.await()
//                result(taskResult)
//            } catch (error: Exception) {
//                Log.i(TAG, error.toString())
//            }
//        }
//    }

    /**
     * Запись данных о домах в DB
     * @param houses - Список персонажей (модель HouseRes - модель ответа из сети)
     * необходимо произвести трансформацию данных
     * @param complete - колбек о завершении вставки записей db
     */
    fun insertHouses(houses: MutableList<HouseRes>, complete: () -> Unit) {
        localScope.launch {
            try {
                val task = async(Dispatchers.IO) {
                    appDatabaseDao.insertAll(houses.map { it.transformToHouse() })
                    return@async true
                }
                task.await()
                complete?.invoke()
            } catch (error: Exception) {
                Log.i(TAG, error.toString())
            }
        }
    }

    /**
     * При вызове данного метода необходимо выполнить удаление всех записей в db
     * @param complete - колбек о завершении очистки db
     */
    fun dropDb(complete: () -> Unit) {
        localScope.launch {
            try {
                val task = async(Dispatchers.IO) {
                    appDatabaseDao.clear()
                    return@async true
                }
                task.await()
                complete()
            } catch (error: Exception) {
                Log.i(TAG, error.toString())
            }
        }
    }

    /**
     * Метод возвращет true если в базе нет ни одной записи, иначе false
     * @param result - колбек о завершении очистки dbt
     */
    fun isNeedUpdate(result: (isNeed: Boolean) -> Unit) {

        localScope.launch {
            try {
                val task = async(Dispatchers.IO) {
                    return@async appDatabaseDao.getAll().isEmpty()
                }
                val taskResult = task.await()
                result(taskResult)
            } catch (error: Exception) {
                Log.i(TAG, error.toString())
            }
        }
    }


}
