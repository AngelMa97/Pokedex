package com.angelme.pokedex.ui.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.angelme.pokedex.R
import com.angelme.pokedex.databinding.FragmentPokemonListBinding
import com.angelme.pokedex.repository.WorkResult
import com.angelme.pokedex.ui.MainViewModel
import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.pokemondetail.PokemonDetailActivity
import com.angelme.pokedex.ui.pokemondetail.PokemonDetailActivity.Companion.POKEMON_DETAIL
import com.angelme.pokedex.util.Generation
import com.angelme.pokedex.util.PokemonType
import com.google.android.material.chip.Chip
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment(), PokedexAdapter.PokedexItemListener {

    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var generation = Generation.FIRST

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPokemonListBinding.inflate(inflater)
        setViews(inflater)
        setObservers()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.removeAllFilters()
    }

    private fun setViews(inflater: LayoutInflater) {
        binding.apply {
            pickGenerationButton.setOnClickListener {
                val popUp = PopupMenu(requireContext(), it)
                popUp.menuInflater.inflate(R.menu.generation_menu, popUp.menu)
                popUp.setOnMenuItemClickListener {
                    generation = when (it.itemId) {
                        R.id.first_generation -> Generation.FIRST
                        R.id.second_generation -> Generation.SECOND
                        R.id.third_generation -> Generation.THIRD
                        R.id.fourth_generation -> Generation.FOURTH
                        R.id.fifth_generation -> Generation.FIFTH
                        R.id.sixth_generation -> Generation.SIXTH
                        R.id.seventh_generation -> Generation.SEVENTH
                        R.id.eighth_generation -> Generation.EIGHTH
                        else -> Generation.NINTH
                    }
                    viewModel.getPokemonByGeneration(generation)
                    true
                }
                popUp.show()
            }
            for (type in PokemonType.entries) {
                val chip = inflater.inflate(R.layout.type_chip, filterBy, false) as Chip
                chip.text = type.name
                chip.setChipBackgroundColorResource(type.color)
                chip.setOnCheckedChangeListener { _, _ ->
                    viewModel.modifyFilters(type)
                }
                filterBy.addView(chip)
            }
            pokedexList.layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
    }

    private fun setObservers() {
        viewModel.apply {
            binding.apply {
                iuState.observe(viewLifecycleOwner) {
                    when (it) {
                        is WorkResult.Success -> {
                            pokedexList.adapter = PokedexAdapter(
                                it.data, resources, requireContext(), this@PokemonListFragment
                            )
                            pickGenerationButton.isEnabled = true
                            loadingOverlay.visibility = View.GONE
                        }
                        is WorkResult.Loading -> {
                            pickGenerationButton.isEnabled = false
                            loadingOverlay.visibility = View.VISIBLE
                        }
                        else -> { }
                    }
                }
            }
        }
    }

    override fun onPokedexItemClickListener(pokemon: Pokemon) {
        val ntnt = Intent(requireContext(), PokemonDetailActivity::class.java)
        ntnt.putExtra(POKEMON_DETAIL, Gson().toJson(pokemon))
        startActivity(ntnt)
    }
}