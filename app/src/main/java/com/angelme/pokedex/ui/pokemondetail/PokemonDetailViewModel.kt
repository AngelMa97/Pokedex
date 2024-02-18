package com.angelme.pokedex.ui.pokemondetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelme.pokedex.domain.AddPokemonToMyListUseCase
import com.angelme.pokedex.domain.GetMyPokemonByIdUseCase
import com.angelme.pokedex.domain.RemovePokemonFromMyListUseCase
import com.angelme.pokedex.repository.WorkResult
import com.angelme.pokedex.ui.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getMyPokemonByIdUseCase: GetMyPokemonByIdUseCase,
    private val addPokemonToMyListUseCase: AddPokemonToMyListUseCase,
    private val removePokemonListUseCase: RemovePokemonFromMyListUseCase
) : ViewModel() {
    private var _myPokemonUiState = MutableLiveData<WorkResult<Pokemon?>>()
    val myPokemonListUiState: LiveData<WorkResult<Pokemon?>> get() = _myPokemonUiState

    fun getMyPokemonById(id: Int) {
        _myPokemonUiState.postValue(WorkResult.Loading)
        viewModelScope.launch {
            getMyPokemonByIdUseCase(id).collect {
                _myPokemonUiState.postValue(WorkResult.Success(it))
            }
        }
    }

    fun addPokemonToMyList(pokemon: Pokemon) {
        _myPokemonUiState.postValue(WorkResult.Loading)
        viewModelScope.launch {
            addPokemonToMyListUseCase(pokemon)
        }
    }

    fun removePokemonFromMyList(pokemon: Pokemon) {
        _myPokemonUiState.postValue(WorkResult.Loading)
        viewModelScope.launch {
            removePokemonListUseCase(pokemon)
        }
    }
}
