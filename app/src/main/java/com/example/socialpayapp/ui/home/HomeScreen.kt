package com.example.socialpayapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onScanClicked: () -> Unit = {},
    onPayClicked: () -> Unit = {}
) {
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Введите сумму") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onScanClicked()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сканировать QR-код")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onPayClicked()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = amount.isNotEmpty()
        ) {
            Text("Оплатить")
        }
    }
}