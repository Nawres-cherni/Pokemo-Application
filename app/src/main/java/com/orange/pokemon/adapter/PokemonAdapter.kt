package com.orange.pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.orange.pokemon.R
import com.orange.pokemon.data.PokemonEntity
import com.orange.pokemon.databinding.ItemLayoutBinding


class PokemonAdapter : ListAdapter<PokemonEntity, PokemonAdapter.PokemonViewholder>(PokemonDiffUtils()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.PokemonViewholder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context)
            ,parent,false
        )
        return PokemonViewholder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewholder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class PokemonViewholder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transactionEntity: PokemonEntity) {
            val context=itemView.context
            with(binding) {
                pokemonName.text = transactionEntity.name
                pokemonSpeed.text = transactionEntity.speed.toString()
                Glide.with(context)
                    .load(transactionEntity.image)
                    .placeholder(R.drawable.ic_launcher_background)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(binding.pokemonImage)

            }
        }

    }



}

class PokemonDiffUtils : DiffUtil.ItemCallback<PokemonEntity>() {
    override fun areItemsTheSame(
        oldItem: PokemonEntity,
        newItem: PokemonEntity
    ): Boolean {
        TODO("Not yet implemented")
    }



    override fun areContentsTheSame(
        oldItem: PokemonEntity,
        newItem: PokemonEntity
    ): Boolean {
        TODO("Not yet implemented")
    }

}