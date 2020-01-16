package ru.android.ufstask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.android.ufstask.R
import ru.android.ufstask.models.ArticleItem
import ru.android.ufstask.databinding.ItemCharacterBinding

/*
 * Created by yasina on 2020-01-14
*/
class NewsAdapter(
    private val clickListener: (ArticleItem) -> Unit
) : ListAdapter<ArticleItem, NewsAdapter.ViewHolder>(
    ArticleDiffCallback()
) {

    init {
        setHasStableIds(true)
    }

    var characters: MutableList<ArticleItem> = mutableListOf()

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
            character: ArticleItem,
            clickListener: (ArticleItem) -> Unit
        ) {
            binding.listItemCharacterName = if (character.title!!.isEmpty()) "Information is unknown"
            else character.title
            binding.listItemCharacterInfo = character.publishedAt
            binding.root.setOnClickListener{ clickListener.invoke(character) }
        }
    }
}

class ArticleDiffCallback : DiffUtil.ItemCallback<ArticleItem>() {

    override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
        return oldItem.title.equals(newItem.title)
    }

    override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
        return oldItem.title.equals(newItem.title)
    }
}
