package com.angelme.pokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelme.pokedex.domain.GetPokemonUseCase
import com.angelme.pokedex.repository.WorkResult
import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.util.Generation
import com.angelme.pokedex.util.PokemonType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
) : ViewModel() {

    private var _iuState = MutableLiveData<WorkResult<List<Pokemon>>>()
    val iuState: LiveData<WorkResult<List<Pokemon>>> get() = _iuState

    private var pokemonListGeneration = emptyList<Pokemon>()
    private var filters = mutableListOf<PokemonType>()

    fun getPokemonByGeneration(generation: Generation) {
        _iuState.postValue(WorkResult.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pokemonList = mutableListOf<Pokemon>()
                for (i in 0..<generation.noPokemon) {
                    pokemonList.add(
                        getPokemonUseCase(generation.offset + i + 1)
                    )
                }
                pokemonListGeneration = pokemonList
                if (filters.isNotEmpty()) {
                    _iuState.postValue(
                        WorkResult.Success(
                            pokemonListGeneration.filter { pokemon ->
                                pokemon.types.any { type ->
                                    filters.contains(type)
                                }
                            }
                        )
                    )
                } else {
                    _iuState.postValue(WorkResult.Success(pokemonList))
                }
            } catch (e: Exception) {
                _iuState.postValue(WorkResult.Error(e))
            }
        }
    }

    fun modifyFilters(type: PokemonType) {
        if (filters.any { t -> t == type }) {
            filters.remove(type)
        } else {
            filters.add(type)
        }
        if (filters.isEmpty()) {
            _iuState.postValue(WorkResult.Success(pokemonListGeneration))
        } else {
            _iuState.postValue(
                WorkResult.Success(
                    pokemonListGeneration.filter { pokemon ->
                        pokemon.types.any { type ->
                            filters.contains(type)
                        }
                    }
                )
            )
        }
    }

    init {
        getPokemonByGeneration(Generation.FIRST)
    }

}