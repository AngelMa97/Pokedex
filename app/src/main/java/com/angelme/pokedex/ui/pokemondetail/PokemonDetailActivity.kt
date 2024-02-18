package com.angelme.pokedex.ui.pokemondetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelme.pokedex.R
import com.angelme.pokedex.databinding.ActivityPokemonDetailBinding
import com.angelme.pokedex.repository.WorkResult
import com.angelme.pokedex.ui.model.Pokemon
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonDetailBinding
    private lateinit var pokemon: Pokemon
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pokemon = Gson().fromJson(getIntent().getStringExtra(POKEMON_DETAIL), Pokemon::class.java)

        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViews()

        setObservers()
    }

    private fun setViews() {
        pokemon?.let {
            binding.apply {
                //Toolbar
                topAppBar.apply {
                    title = it.name
                    setNavigationOnClickListener {
                        onBackPressedDispatcher.onBackPressed()
                    }
                }

                //Images
                Glide.with(this@PokemonDetailActivity)
                    .load(pokemon.sprites[0])
                    .into(pokemonSprite)
                Glide.with(this@PokemonDetailActivity)
                    .load(pokemon.sprites[1])
                    .into(pokemonShiny)

                //Type
                primaryType.apply {
                    text = pokemon.types[0].name
                    setChipBackgroundColorResource(pokemon.types[0].color)
                }
                if (pokemon.types.size > 1) {
                    secondType.apply {
                        text = pokemon.types[1].name
                        setChipBackgroundColorResource(pokemon.types[1].color)
                    }
                } else secondType.visibility = View.GONE

                detailsList.adapter = ArrayAdapter(
                    this@PokemonDetailActivity,
                    android.R.layout.simple_list_item_1,
                    pokemon.abilities
                )
                statsRv.apply {
                    addItemDecoration(
                        DividerItemDecoration(
                            this@PokemonDetailActivity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                    layoutManager = LinearLayoutManager(
                        this@PokemonDetailActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    adapter = PokemonStatsAdapter(pokemon.stats, resources)
                }

                tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab) {
                        when (tab.position) {
                            0 -> {
                                detailsList.visibility = View.VISIBLE
                                statsRv.visibility = View.GONE
                            }

                            1 -> {
                                detailsList.visibility = View.GONE
                                statsRv.visibility = View.VISIBLE
                            }
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {}

                    override fun onTabReselected(tab: TabLayout.Tab?) {}

                })

            }
        }
    }

    private fun setObservers() {
        binding.apply {
            pokemonDetailViewModel.apply {
                getMyPokemonById(pokemon.id)
                myPokemonListUiState.observe(this@PokemonDetailActivity) {
                    when (it) {
                        is WorkResult.Error -> progressIndicator.hide()
                        WorkResult.Loading -> {
                            loadingOverlay.visibility = View.VISIBLE
                            progressIndicator.show()
                            toFavorites.isEnabled = false
                        }

                        is WorkResult.Success -> {
                            loadingOverlay.visibility = View.GONE
                            progressIndicator.hide()
                            toFavorites.isEnabled = true
                            toFavorites.setOnClickListener {
                                addPokemonToMyList(pokemon)
                            }
                            it.data?.let {
                                toFavorites.setImageDrawable(
                                    resources.getDrawable(R.drawable.ic_remove, null)
                                )
                                toFavorites.setOnClickListener {
                                    removePokemonFromMyList(pokemon)
                                }
                            } ?: toFavorites.setImageDrawable(
                                resources.getDrawable(R.drawable.ic_check, null)
                            )
                        }
                    }
                }
            }
        }
    }

    companion object {
        val POKEMON_DETAIL = "POKEMON_DETAIL"
    }
}