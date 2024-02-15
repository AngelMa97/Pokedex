package com.angelme.pokedex.ui.pokedex

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelme.pokedex.databinding.PokedexItemBinding
import com.angelme.pokedex.ui.model.Pokemon
import com.bumptech.glide.Glide

class PokedexAdapter(
    private val pokedex: List<Pokemon>,
    private val resources: Resources,
    private val context: Context,
    private val pokedexItemListener: PokedexItemListener
) :
    RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder =
        PokedexViewHolder(
            PokedexItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = pokedex.size

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        holder.bind(pokedex[position], resources, context, pokedexItemListener)
    }

    class PokedexViewHolder(val view: PokedexItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: Pokemon, resources: Resources, context: Context, pokedexItemListener: PokedexItemListener) {
            view.apply {
                Glide
                    .with(context)
                    .load(item.sprites[0])
                    .into(pokemonImage)

                pokemonName.text = "${ item.id }.- ${ item.name }"

                primaryType.apply {
                    text = item.types[0].name
                    setChipBackgroundColorResource(item.types[0].color)
                }
                if (item.types.size > 1) {
                    secondType.apply {
                        text = item.types[1].name
                        setChipBackgroundColorResource(item.types[1].color)
                    }
                } else secondType.visibility = View.GONE

                root.setOnClickListener { pokedexItemListener.onPokedexItemClickListener(item) }
            }
        }
    }

    interface PokedexItemListener {
        fun onPokedexItemClickListener(pokemon: Pokemon)
    }
}