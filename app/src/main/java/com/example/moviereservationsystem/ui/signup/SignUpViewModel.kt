package com.example.moviereservationsystem.ui.signup

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.moviereservationsystem.domain.usecase.SingUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SingUpUseCase) : ViewModel(){

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun updateEmail(inputEmail: String) {
        _uiState.update { currentState ->
            val isEnabled = isValidEmail(inputEmail) && isValidPassword(currentState.password) && currentState.name.isNotEmpty()
            currentState.copy(
                email = inputEmail,
                isSignUpEnabled = isEnabled
            )
        }
    }

    fun updatePassword(inputPassword: String) {
        _uiState.update { currentState ->
            val isEnabled = isValidEmail(currentState.email) && isValidPassword(inputPassword) && currentState.name.isNotEmpty()
            currentState.copy(
                password = inputPassword,
                isSignUpEnabled = isEnabled
            )
        }
    }

    fun updateName(newName: String) {
        _uiState.update { currentState ->
            val isEnabled = isValidEmail(currentState.email) && isValidPassword(currentState.password) && newName.isNotEmpty()
            currentState.copy(
                name = newName,
                isSignUpEnabled = isEnabled
            )
        }
    }

    private fun isValidPassword(password: String):Boolean = password.length >= 6

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()


    fun signUp(){
        if (!_uiState.value.isSignUpEnabled) return


        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = signUpUseCase(_uiState.value.name, _uiState.value.email, _uiState.value.password)

            result.onSuccess {
                Log.d("SignUp", "SignUp exitoso: ${it.user?.email}")
            }.onFailure {
                Log.e("SignUp", "Error en el SignUp", it)
            }
        }


    }
}