package com.angelme.pokedex.ui.pokemondetail

import android.app.ActionBar.LayoutParams
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelme.pokedex.R
import com.angelme.pokedex.databinding.PokemonStatItemBinding
import com.angelme.pokedex.ui.model.Stat

class PokemonStatsAdapter(private val stats: List<Stat>, val resources: Resources) :
    RecyclerView.Adapter<PokemonStatsAdapter.PokemonStatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonStatViewHolder =
        PokemonStatViewHolder(
            PokemonStatItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = stats.size

    override fun onBindViewHolder(holder: PokemonStatViewHolder, position: Int) {
        holder.bind(stats[position], resources)
    }


    class PokemonStatViewHolder(val view: PokemonStatItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(item: Stat, resources: Resources) {
            view.apply {
                statIcon.setImageDrawable(
                    when (item.name) {
                        "attack", "special-attack" -> resources.getDrawable(R.drawable.sword)
                        "defense", "special-defense" -> resources.getDrawable(R.drawable.shield)
                        "hp" -> resources.getDrawable(R.drawable.health)
                        else -> resources.getDrawable(R.drawable.dash)
                    }
                )
                statName.text = item.name
                statValue.text = "${item.value}"
            }
        }
    }
}