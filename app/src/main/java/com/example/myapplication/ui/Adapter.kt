package com.example.myapplication.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemNodeBinding
import com.example.myapplication.data.NodeData


class Adapter(private val ClickNode: (Int) -> Unit) : ListAdapter<NodeData, Adapter.ItemViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(
            ItemNodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            ClickNode(getItem(position).id)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<NodeData>() {
            override fun areItemsTheSame(oldItem: NodeData, newItem: NodeData): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: NodeData, newItem: NodeData): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ItemViewHolder(private var binding: ItemNodeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(node: NodeData) {
            binding.nodesListItem.text = "id: " + node.id + "  value: " + node.value
            if (node.Parents && node.Children) {
                binding.root.setBackgroundColor(Color.RED)
            } else if (node.Parents) {
                binding.root.setBackgroundColor(Color.BLUE)
            } else if (node.Children) {
                binding.root.setBackgroundColor(Color.YELLOW)
            } else {
                binding.root.setBackgroundColor(Color.WHITE)
            }

        }
    }
}
