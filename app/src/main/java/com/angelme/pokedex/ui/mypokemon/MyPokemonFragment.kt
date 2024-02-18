package com.angelme.pokedex.ui.mypokemon

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.angelme.pokedex.R
import com.angelme.pokedex.databinding.FragmentMyPokemonBinding
import com.angelme.pokedex.repository.WorkResult
import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.pokedex.PokedexAdapter
import com.angelme.pokedex.ui.pokemondetail.PokemonDetailActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPokemonFragment : Fragment(), PokedexAdapter.PokedexItemListener {

    private lateinit var binding: FragmentMyPokemonBinding
    private val myPokemonViewModel: MyPokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyPokemonBinding.inflate(inflater)

        setViews()
        setObservers()

        return binding.root
    }

    private fun setViews() {
        binding.pokedexList.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }

    private fun setObservers() {
        binding.apply {
            myPokemonViewModel.uiStateMyPokemon.observe(viewLifecycleOwner) {
                when (it) {
                    is WorkResult.Error -> TODO()
                    WorkResult.Loading -> {
                        loadingOverlay.visibility = View.VISIBLE
                        pokedexList.visibility = View.INVISIBLE
                    }
                    is WorkResult.Success -> {
                        if (it.data.isEmpty()) {
                            noPokemonInListView.visibility = View.VISIBLE
                            pokedexList.visibility = View.INVISIBLE
                        } else {
                            noPokemonInListView.visibility = View.GONE
                            pokedexList.adapter = PokedexAdapter(
                                it.data, resources, requireContext(), this@MyPokemonFragment
                            )
                            pokedexList.visibility = View.VISIBLE
                        }
                        loadingOverlay.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onPokedexItemClickListener(pokemon: Pokemon) {
        val ntnt = Intent(requireContext(), PokemonDetailActivity::class.java)
        ntnt.putExtra(PokemonDetailActivity.POKEMON_DETAIL, Gson().toJson(pokemon))
        startActivity(ntnt)
    }

}
