package com.example.laba4

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.transform.RoundedCornersTransformation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.laba4.databinding.CardPersonBinding
import kotlinx.coroutines.flow.Flow


class PersonAdapter(var listperson: List<Person>, private val clickCard: (Person) -> Unit, private val clickCardLike: (Person) -> Unit)
    : RecyclerView.Adapter<PersonAdapter.PerHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerHolder {
        val binding = CardPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PerHolder(binding, clickCard, clickCardLike)
    }

    override fun onBindViewHolder(holder: PerHolder, position: Int) {
        val person = listperson[position]
        holder.bind(person)
    }
    override fun getItemCount(): Int {
        return listperson.size
    }
    inner class PerHolder internal constructor(private val binding: CardPersonBinding, private val clickCard: (Person) -> Unit,
                                               private val clickCardLike: (Person) -> Unit)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(person: Person) = binding.run{
            name.text = person.name
            sex.text = person.sex
            date.text = person.date
            info.text = person.info
            photo.load(person.photoPerson){
                transformations(RoundedCornersTransformation(50f))
            }
            binding.cardId.setOnClickListener {
                clickCard.invoke(person)
            }
            binding.like.setOnClickListener {
                clickCardLike.invoke(person)
            }
        }
    }


}