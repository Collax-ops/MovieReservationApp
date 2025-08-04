package com.example.moviereservationsystem.ui.screens.paymentHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereservationsystem.R
import com.example.moviereservationsystem.data.local.datastore.SessionDataStore
import com.example.moviereservationsystem.domain.usecase.GetUserPaymentsUseCase
import com.example.moviereservationsystem.ui.screens.paymentHistory.model.PaymentHistoryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentHistoryViewModel @Inject constructor(
    private val getUserPaymentsUseCase: GetUserPaymentsUseCase,
    private val sessionDataStore: SessionDataStore
) : ViewModel() {
    private val _uiState = MutableStateFlow(PaymentHistoryUiState())
    val uiState: StateFlow<PaymentHistoryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val userId = sessionDataStore.getUserId()

            getUserPaymentsUseCase(userId.toString()).collectLatest { payments ->
                _uiState.value = PaymentHistoryUiState(
                    history = payments.map { payment ->
                        PaymentHistoryUiModel(
                            id = payment.ticketId.toString(),
                            date = payment.transactionDate.toString(),
                            amount = payment.amount,
                            status = payment.paymentStatus,
                            methodIconRes = R.drawable.paypal_icon
                        )
                    }
                )
            }
        }
    }
}