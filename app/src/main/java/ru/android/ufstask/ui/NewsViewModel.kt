package ru.android.ufstask.ui

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.android.ufstask.models.ArticleItem
import ru.android.ufstask.extensions.isNetworkAvailable
import java.util.*
import ru.android.ufstask.helper.ResultWrapper.*
import ru.android.ufstask.models.ErrorResponse
import ru.android.ufstask.usecase.NewsDbUseCase
import ru.android.ufstask.usecase.NewsNetUseCase

/*
 * Created by yasina on 2020-01-14
*/
class NewsViewModel(
    private val netUseCase: NewsNetUseCase,
    private val dbUseCase: NewsDbUseCase,
    val applicationContext: Context)
    : ViewModel() {

    private var data = MutableLiveData<LoadResult<MutableList<ArticleItem>>>()

    init {
        data.value = LoadResult.Loading(mutableListOf())
        updateFromNet()
    }

    fun syncData() : LiveData<LoadResult<Boolean>>{
        val result : MutableLiveData<LoadResult<Boolean>> = MutableLiveData()

        result.value = LoadResult.Loading(false)

        viewModelScope.launch(Dispatchers.IO) {
//            repository.getAllNewsFromDb {
//
//            }
            if (!applicationContext.isNetworkAvailable){
                result.postValue(LoadResult.Error("Интернет недоступен, приложение может работать некорректно"))
                return@launch
            }

            result.postValue(LoadResult.Success(true))

        }
        return result
    }

    fun updateFromNet(){
        netUseCase.getNews(getCountry(), API_KEY) {
            when (it) {
                is NetworkError -> showNetworkError()
                is GenericError -> showGenericError(it.error)
                is Success -> {
                    dbUseCase.insertNews(it.value.articles!!){
                        if(it){
                            updateNews()
                        }else{

                        }
                    }
                }
            }
        }

    }

    private fun showNetworkError(){

    }

    private fun showGenericError(errorMessage: ErrorResponse?){

    }

    private fun updateNews(){
        dbUseCase.getAllNews {
            data.postValue(LoadResult.Success(it))
        }
    }

    fun getData(): LiveData<LoadResult<MutableList<ArticleItem>>> {
        val result = MediatorLiveData<LoadResult<MutableList<ArticleItem>>>()
        val filterF = {
            result.value = data.value
        }
        result.addSource(data) {  filterF.invoke() }
        return result
    }

    private fun getCountry(): String {
        val locale = Locale.getDefault()
        val country = locale.country.toString()
        return country.toLowerCase()
    }

    companion object {
        private const val API_KEY = "27ac93231d68496da29573697ec06b91"
    }

}


sealed class LoadResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
){
    class Success<T>(data: T?) : LoadResult<T>(data)
    class Loading<T>(data: T?) : LoadResult<T>(data)
    class Error<T>(message: String) : LoadResult<T>(errorMessage = message)
}