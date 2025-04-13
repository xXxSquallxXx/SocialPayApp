package com.example.socialpayapp.ui.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import com.example.socialpayapp.domain.usecase.GetTransactionsUseCase
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HistoryScreen(getTransactionsUseCase: GetTransactionsUseCase) {
    var transactions by remember { mutableStateOf<List<com.example.socialpayapp.data.model.Transaction>>(emptyList()) }

    LaunchedEffect(Unit) {
        getTransactionsUseCase.execute().collectLatest { newTransactions ->
            transactions = newTransactions
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            items = transactions,
            key = { transaction -> transaction.id }
        ) { transaction ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Сумма: ${transaction.amount}, Получатель: ${transaction.recipient}, Дата: ${transaction.date}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}