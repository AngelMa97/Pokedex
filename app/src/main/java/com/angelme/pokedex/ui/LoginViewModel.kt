package com.angelme.pokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelme.pokedex.domain.CreateUserWithEmailAndPasswordUseCase
import com.angelme.pokedex.domain.GetUserUseCase
import com.angelme.pokedex.domain.SignInWithEmailAndPasswordUseCase
import com.angelme.pokedex.repository.WorkResult
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    private val createUserWithEmailAndPasswordUseCase: CreateUserWithEmailAndPasswordUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private var _uiState = MutableLiveData<WorkResult<FirebaseUser>>()
    val uiState: LiveData<WorkResult<FirebaseUser>> get() = _uiState

    fun signInWithEmailAndPassword(email: String, password: String) {
        _uiState.postValue(WorkResult.Loading)
        viewModelScope.launch {
            try {
                val result = signInWithEmailAndPasswordUseCase(email, password)
                result?.let {
                    _uiState.postValue(
                        WorkResult.Success(result)
                    )
                } ?: _uiState.postValue(WorkResult.Error(Exception("Something goes wrong")))
            } catch (ex: Exception) {
                _uiState.postValue(WorkResult.Error(ex))
            }
        }
    }

    fun signUpWitEmailAndPassword(email: String, password: String) {
        _uiState.postValue(WorkResult.Loading)
        viewModelScope.launch {
            try {
                val result = createUserWithEmailAndPasswordUseCase(email, password)
                result?.let {
                    _uiState.postValue(
                        WorkResult.Success(result)
                    )
                } ?: _uiState.postValue(WorkResult.Error(Exception("Something goes wrong")))
            } catch (ex: Exception) {
                _uiState.postValue(WorkResult.Error(ex))
            }
        }
    }

    fun getLoggedUser() {
        val result = getUserUseCase()
        result?.let { _uiState.postValue(WorkResult.Success(it)) }
    }

    init {
        getLoggedUser()
    }
}