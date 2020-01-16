package ru.android.ufstask.ui

import android.content.Context
import androidx.lifecycle.*
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import ru.android.ufstask.models.ArticleItem
import ru.android.ufstask.extensions.isNetworkAvailable

import ru.android.ufstask.helper.ResultWrapper.*
import ru.android.ufstask.models.ErrorResponse
import ru.android.ufstask.models.NewsRes
import ru.android.ufstask.usecase.NewsDbUseCase
import ru.android.ufstask.usecase.NewsNetUseCase
import java.util.*
import java.util.concurrent.TimeUnit

/*
 * Created by yasina on 2020-01-14
*/
class NewsViewModel(
    private val netUseCase: NewsNetUseCase,
    private val dbUseCase: NewsDbUseCase,
    val applicationContext: Context)
    : ViewModel() {

    private var data = MutableLiveData<LoadResult<MutableList<ArticleItem>>>()
    private val disposable: Disposable

    init {
        data.value = LoadResult.Loading()
        getNewsFromNet()

        disposable =
            Observable.interval(2, TimeUnit.MINUTES)
                .doOnNext {
                    syncData()
                }
                .subscribe()
    }

    fun syncData() : LiveData<LoadResult<Boolean>>{
        val result : MutableLiveData<LoadResult<Boolean>> = MutableLiveData()
        data.postValue(LoadResult.Loading())

        if (!applicationContext.isNetworkAvailable){
            result.postValue(LoadResult.Error("Интернет недоступен, приложение будет работать в оффлайн режиме"))
            getNewsFromDb() 
        }else{
            getNewsFromNet()
        }
        
        return result
    }

    fun getNewsFromNet(){
        netUseCase.getNews(getCountry(), API_KEY) {
            when (it) {
                is NetworkError -> showNetworkError()
                is GenericError -> showGenericError(it.error)
                is Success -> updateNewsInDb(it.value)
            }
        }
    }
    
    private fun updateNewsInDb(news: NewsRes){
        dbUseCase.insertNews(news.articles!!){
            if(it){
                getNewsFromDb()
            }else{
                data.postValue(LoadResult.Error("Не получилось сохранить данные в БД"))
            }
        }
    }

    private fun showNetworkError(){
        data.postValue(LoadResult.Error("Интернет недоступен"))
    }

    private fun showGenericError(errorMessage: ErrorResponse?){
        data.postValue(LoadResult.Error(errorMessage!!.message!!))
    }

    private fun getNewsFromDb(){
        dbUseCase.getAllNews {
            if(it.isNotEmpty())
                data.postValue(LoadResult.Success(it))
            else
                data.postValue(LoadResult.Error("Пустой список"))
        }
    }

    fun updateData(): LiveData<LoadResult<MutableList<ArticleItem>>> {
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

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
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
    class Loading<T> : LoadResult<T>(null)
    class Error<T>(message: String) : LoadResult<T>(errorMessage = message)
}