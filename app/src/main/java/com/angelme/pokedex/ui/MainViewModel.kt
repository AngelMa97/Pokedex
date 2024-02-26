package com.angelme.pokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelme.pokedex.domain.GetUserUseCase
import com.angelme.pokedex.domain.SignOutUseCase
import com.angelme.pokedex.repository.WorkResult
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private var _uiState = MutableLiveData<WorkResult<FirebaseUser?>>()
    val uiState: LiveData<WorkResult<FirebaseUser?>> get() = _uiState

    fun signOut() {
        _uiState.value = WorkResult.Loading
        try {
            _uiState.postValue(WorkResult.Success(signOutUseCase()))
        } catch (ex: Exception) {
            _uiState.postValue(WorkResult.Error(ex))
        }
    }

}