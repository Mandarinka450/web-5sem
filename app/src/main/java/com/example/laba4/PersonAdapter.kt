package com.example.laba4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.laba4.databinding.CardPersonBinding


class PersonAdapter(var list: List<String> = listOf())
    : RecyclerView.Adapter<PersonAdapter.PerHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerHolder {
        val binding = CardPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PerHolder(binding)
    }

    override fun onBindViewHolder(holder: PerHolder, position: Int) {
        val text = list[position]
        holder.bind(text)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    inner class PerHolder internal constructor(private val binding: CardPersonBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(text: String) = binding.run{
            textView.text = text
        }
    }


}