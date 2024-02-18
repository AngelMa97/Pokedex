package com.angelme.pokedex.ui.mypokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelme.pokedex.domain.GetMyPokemonListUseCase
import com.angelme.pokedex.repository.WorkResult
import com.angelme.pokedex.ui.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPokemonViewModel @Inject constructor(
    private val getMyPokemonListUseCase: GetMyPokemonListUseCase
) : ViewModel() {

    private var _iuStateMyPokemon = MutableLiveData<WorkResult<List<Pokemon>>>()
    val uiStateMyPokemon: LiveData<WorkResult<List<Pokemon>>> get() = _iuStateMyPokemon

    private fun getMyPokemonList() {
        _iuStateMyPokemon.postValue(WorkResult.Loading)
        viewModelScope.launch {
            getMyPokemonListUseCase().collect {
                _iuStateMyPokemon.postValue(WorkResult.Success(it))
            }
        }
    }

    init {
        getMyPokemonList()
    }
}