package ru.android.ufstask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.android.ufstask.R
import ru.android.ufstask.common.extensions.transformToHouseItem
import ru.android.ufstask.data.local.entities.HouseItem

/*
 * Created by yasina on 2020-01-14
*/
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter{

        }
        house_characters_recycler_view.adapter = adapter
        house_characters_recycler_view.itemAnimator!!.changeDuration = 0

        viewModel.getData().observe(this, Observer {
            it?.let {
                var characters: MutableList<HouseItem> = mutableListOf()

                it.forEach { i ->
                    characters.add(i.transformToHouseItem())
                }
                adapter.characters = characters
                adapter.notifyDataSetChanged()
            }
        })


    }



}