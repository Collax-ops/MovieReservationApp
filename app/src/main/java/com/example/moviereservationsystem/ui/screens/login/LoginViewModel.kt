package com.example.moviereservationsystem.ui.screens.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereservationsystem.data.local.datastore.SessionDataStore
import com.example.moviereservationsystem.domain.usecase.LoginUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sessionDataStore: SessionDataStore
) : ViewModel(){



    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateEmail(inputEmail: String) {
        _uiState.update { currentState ->
            val isEnabled = isValidEmail(inputEmail) && isValidPassword(currentState.password)
            currentState.copy(
                email = inputEmail,
                isLoginEnabled = isEnabled
            )
        }
    }

    fun updatePassword(inputPassword: String) {
        _uiState.update { currentState ->
            val isEnabled = isValidEmail(currentState.email) && isValidPassword(inputPassword)
            currentState.copy(
                password = inputPassword,
                isLoginEnabled = isEnabled
            )
        }
    }

    private fun isValidPassword(password: String):Boolean = password.length >= 6

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()


    fun login() {
        if (!_uiState.value.isLoginEnabled) return

        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = loginUseCase(_uiState.value.email, _uiState.value.password)
            result.onSuccess {
                val userId = it.user?.uid
                if (userId != null) sessionDataStore.saveUserId(userId)
                _uiState.update { it.copy(isLoading = false, navigateToHome = true) }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onNavigationHandled() {
        _uiState.update { it.copy(navigateToHome = false) }
    }

}