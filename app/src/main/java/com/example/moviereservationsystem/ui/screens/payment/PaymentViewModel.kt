package com.example.moviereservationsystem.ui.screens.payment

import android.content.ContentValues.TAG
import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.moviereservationsystem.domain.usecase.CreatePayPalOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val createPayPalOrderUseCase: CreatePayPalOrderUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentUiState())
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    @OptIn(UnstableApi::class)
    fun onMethodSelected(method: PaymentMethod) {
        _uiState.update { it.copy(selectedMethod = method) }
    }

    @OptIn(UnstableApi::class)
    fun onPayClicked(amount: Double) {
        if (_uiState.value.selectedMethod == PaymentMethod.PAYPAL) {
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true) }
                try {
                    val orderId = createPayPalOrderUseCase(amount)
                    Log.d(TAG, "Received orderId = $orderId")
                    _uiState.update {
                        it.copy(isLoading = false, orderId = orderId)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error creating order", e)
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = e.message)
                    }
                }
            }
        }
    }

    @OptIn(UnstableApi::class)
    fun clearOrderId() {
        _uiState.update { it.copy(orderId = null) }
    }

}