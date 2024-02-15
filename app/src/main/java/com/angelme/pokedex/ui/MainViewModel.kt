package com.angelme.pokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelme.pokedex.domain.GetPokemonByGenerationUseCase
import com.angelme.pokedex.domain.GetPokemonUseCase
import com.angelme.pokedex.repository.WorkResult
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

    private var _iuState = MutableLiveData<WorkResult<List<Pokemon>>>()
    val iuState: LiveData<WorkResult<List<Pokemon>>> get() = _iuState

    fun getPokemonByGeneration(generation: Generation) {
        _iuState.postValue(WorkResult.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            //_pokemonByGeneration.postValue(getPokemonByGenerationUseCase(generation).pokemonList)
            try {
                val pokemonList = mutableListOf<Pokemon>()
                for (i in 0..<generation.noPokemon) {
                    pokemonList.add(
                        getPokemonUseCase(generation.offset + i + 1)
                    )
                }
                _iuState.postValue(WorkResult.Success(pokemonList))
            } catch (e: Exception) {
                _iuState.postValue(WorkResult.Error(e))
            }
        }
    }

    init {
        getPokemonByGeneration(Generation.FIRST)
    }

}