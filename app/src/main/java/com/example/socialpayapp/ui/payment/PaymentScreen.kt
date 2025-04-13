package com.example.socialpayapp.ui.payment

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.socialpayapp.App
import com.example.socialpayapp.data.model.Transaction
import com.example.socialpayapp.data.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PaymentScreen(
    navController: NavController? = null,
    qrCodeData: String? = null
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var amount by remember { mutableStateOf("") }
    var repository by remember { mutableStateOf<TransactionRepository?>(null) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            repository = (context.applicationContext as App).appComponent.transactionRepository()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "QR-код: ${qrCodeData ?: "Не отсканирован"}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Введите сумму") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                scope.launch {
                    repository?.insertTransaction(
                        Transaction(
                            id = 0,
                            date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()),
                            amount = amount,
                            recipient = qrCodeData ?: "Unknown"
                        )
                    )
                    navController?.navigate("history")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = amount.isNotEmpty() && qrCodeData != null
        ) {
            Text("Оплатить")
        }
    }
}