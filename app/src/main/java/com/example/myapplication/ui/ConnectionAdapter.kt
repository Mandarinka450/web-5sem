package com.example.myapplication.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ElementsBinding
import com.example.myapplication.data.Connect


class ConnectionAdapter(private val ClickNode: (Connect) -> Unit) : ListAdapter<Connect, ConnectionAdapter.ItemViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(ElementsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            ClickNode(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Connect>() {
            override fun areItemsTheSame(oldItem: Connect, newItem: Connect): Boolean {
                return oldItem.from.id == newItem.from.id && oldItem.to.id == newItem.to.id
            }
            override fun areContentsTheSame(oldItem: Connect, newItem: Connect): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ItemViewHolder(private var binding: ElementsBinding, ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(connection: Connect) {
            binding.listItem.text = "value: "+ connection.from.value + " ---  value: "+ connection.to.value
            if (connection.isActive) {
                binding.root.setBackgroundColor(Color.DKGRAY)
            } else {
                binding.root.setBackgroundColor(Color.WHITE)
            }
        }
    }
}
