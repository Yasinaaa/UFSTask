package ru.android.ufstask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.android.ufstask.R
import ru.android.ufstask.data.local.entities.HouseItem
import ru.android.ufstask.databinding.ItemCharacterBinding

/*
 * Created by yasina on 2020-01-14
*/
class MainAdapter(
    private val clickListener: (HouseItem) -> Unit
) : ListAdapter<HouseItem, MainAdapter.ViewHolder>(
    CharacterDiffCallback()
) {

    init {
        setHasStableIds(true)
    }

    var characters: MutableList<HouseItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemCharacterBinding>(
            LayoutInflater.from(parent.context), R.layout.item_character, parent, false
        )
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(
            characters[position],
            clickListener
        )

    class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            character: HouseItem,
            clickListener: (HouseItem) -> Unit
        ) {
            binding.listItemCharacterName = if (character.name.isEmpty()) "Information is unknown"
            else character.name
            binding.listItemCharacterInfo = character.region
            binding.root.setOnClickListener{ clickListener.invoke(character) }
        }
    }
}

class CharacterDiffCallback : DiffUtil.ItemCallback<HouseItem>() {

    override fun areItemsTheSame(oldItem: HouseItem, newItem: HouseItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HouseItem, newItem: HouseItem): Boolean {
        return oldItem.id == newItem.id
    }
}
