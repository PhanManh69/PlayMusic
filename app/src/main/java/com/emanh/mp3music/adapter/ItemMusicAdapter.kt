package com.emanh.mp3music.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.emanh.mp3music.R
import com.emanh.mp3music.databinding.ViewholderItemMusicBinding
import com.emanh.mp3music.model.MusicModel
import com.emanh.mp3music.viewModel.MainViewModel

class ItemMusicAdapter(
    private val items: MutableList<MusicModel>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<ItemMusicAdapter.ItemMusicViewHolder>() {

    class ItemMusicViewHolder(
        private val binding: ViewholderItemMusicBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MusicModel) {
            binding.musicModel = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemMusicAdapter.ItemMusicViewHolder {
        val binding: ViewholderItemMusicBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.viewholder_item_music,
            parent,
            false
        )

        return ItemMusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemMusicAdapter.ItemMusicViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            viewModel.selectItem(holder.itemView.context, items[position].id, items[position].url)
        }
    }

    override fun getItemCount(): Int = items.size
}