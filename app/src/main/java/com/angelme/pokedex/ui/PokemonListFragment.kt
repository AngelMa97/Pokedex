package com.angelme.pokedex.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import com.angelme.pokedex.R
import com.angelme.pokedex.databinding.FragmentPokemonListBinding
import com.angelme.pokedex.ui.pokemondetail.PokemonDetailActivity
import com.angelme.pokedex.ui.pokemondetail.PokemonDetailActivity.Companion.POKEMON_DETAIL
import com.angelme.pokedex.util.Generation
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var generation = Generation.FIRST

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPokemonListBinding.inflate(inflater)
        setViews()
        setObservers()
        return binding.root
    }

    private fun setViews() {
        binding.apply {
            pickGenerationButton.setOnClickListener {
                val popUp = PopupMenu(requireContext(), it)
                popUp.menuInflater.inflate(R.menu.generation_menu, popUp.menu)
                popUp.setOnMenuItemClickListener {
                     generation = when(it.itemId) {
                        R.id.first_generation -> Generation.FIRST
                        R.id.second_generation -> Generation.SECOND
                        R.id.third_generation -> Generation.THIRD
                        R.id.fourth_generation -> Generation.FOURTH
                        R.id.fifth_generation -> Generation.FIFTH
                        R.id.sixth_generation -> Generation.SIXTH
                        R.id.seventh_generation -> Generation.SEVENTH
                        R.id.eighth_generation -> Generation.EIGHTH
                        else -> Generation.NINETH
                    }
                    viewModel.getPokemonByGeneration(generation)
                    true
                }
                popUp.show()
            }
            pokedexList.setOnItemClickListener { parent, view, position, id ->
                viewModel.getPokemonById(generation, position)
            }
        }
    }

    private fun setObservers() {
        viewModel.apply {
            pokemonByGeneration.observe(viewLifecycleOwner) {
                binding.pokedexList.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
            }
            pokemonByIndex.observe(viewLifecycleOwner) {
                val ntnt = Intent(requireContext(), PokemonDetailActivity::class.java)
                ntnt.putExtra(POKEMON_DETAIL, Gson().toJson(it))
                startActivity(ntnt)
            }
        }
    }
}