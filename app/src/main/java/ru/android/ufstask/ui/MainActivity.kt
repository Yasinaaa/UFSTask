package ru.android.ufstask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.android.ufstask.R
import ru.android.ufstask.common.extensions.transformToHouse
import ru.android.ufstask.common.extensions.transformToHouseItem
import ru.android.ufstask.data.local.entities.CharacterItem
import ru.android.ufstask.data.local.entities.HouseItem
import ru.android.ufstask.data.local.entities.RelativeCharacter
import ru.android.ufstask.ui.charters_list_screen.ChartersListScreenViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ChartersListScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("dd", "dd")
        val adapter = CharactersAdapter{

        }
        house_characters_recycler_view.adapter = adapter

        viewModel.getData().observe(this, Observer {
            it?.let {
                Log.d("dddddfvgb", "it.size=${it.size}")
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