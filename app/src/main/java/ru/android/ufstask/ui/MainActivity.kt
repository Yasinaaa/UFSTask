package ru.android.ufstask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.android.ufstask.R
import ru.android.ufstask.models.ArticleItem

/*
 * Created by yasina on 2020-01-14
*/
class MainActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = NewsAdapter{
            //TODO onClick
        }
        house_characters_recycler_view.adapter = adapter
        house_characters_recycler_view.itemAnimator!!.changeDuration = 0

        viewModel.syncData()

        swl.setOnRefreshListener {
            viewModel.updateFromNet()
        }

        viewModel.getData().observe(this, Observer<LoadResult<MutableList<ArticleItem>>> {
            when(it){
                is LoadResult.Loading -> {
                    swl.isRefreshing = true
                }
                is LoadResult.Success -> {
                    swl.isRefreshing = false
                    adapter.characters = it.data!!
                    adapter.notifyDataSetChanged()

                    Log.d(MainActivity::class.simpleName, it.data.size.toString()) //todo
                }
                is LoadResult.Error -> {
                    swl.isRefreshing = false
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        it.errorMessage.toString(),
                        Snackbar.LENGTH_INDEFINITE
                    ).show()
                }
            }

        })
    }



}