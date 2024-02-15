package com.angelme.pokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelme.pokedex.domain.GetPokemonByGenerationUseCase
import com.angelme.pokedex.domain.GetPokemonUseCase
import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.util.Generation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPokemonByGenerationUseCase: GetPokemonByGenerationUseCase,
    private val getPokemonUseCase: GetPokemonUseCase,
) : ViewModel() {

    private var _pokemonByGeneration = MutableLiveData<List<Pokemon>>()
    val pokemonByGeneration: LiveData<List<Pokemon>> get() = _pokemonByGeneration

    private var _pokemonByIndex = MutableLiveData<Pokemon>()
    val pokemonByIndex: LiveData<Pokemon> get() = _pokemonByIndex

    fun getPokemonByGeneration(generation: Generation) {
        viewModelScope.launch(Dispatchers.IO) {
            //_pokemonByGeneration.postValue(getPokemonByGenerationUseCase(generation).pokemonList)
            val pokemonList = mutableListOf<Pokemon>()
            for (i in 0..<generation.noPokemon) {
                pokemonList.add(
                    getPokemonUseCase(generation.offset + i + 1)
                )
            }
            _pokemonByGeneration.postValue(pokemonList)
        }
    }

    fun getPokemonById(generation: Generation, index: Int) {
        viewModelScope.launch {
            _pokemonByIndex.postValue(getPokemonUseCase(generation.offset + index + 1))
        }
    }

    init {
        getPokemonByGeneration(Generation.FIRST)
    }

}