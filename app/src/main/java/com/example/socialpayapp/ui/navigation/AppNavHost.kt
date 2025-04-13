package com.example.socialpayapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialpayapp.App
import com.example.socialpayapp.R
import com.example.socialpayapp.ui.history.HistoryScreen
import com.example.socialpayapp.ui.home.HomeScreen
import com.example.socialpayapp.ui.payment.PaymentScreen
import com.example.socialpayapp.ui.scan.ScanScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AppNavHost(navController: NavHostController) {
    val context = LocalContext.current
    var getTransactionsUseCase by remember { mutableStateOf<com.example.socialpayapp.domain.usecase.GetTransactionsUseCase?>(null) }
    var selectedTab by remember { mutableStateOf("Home") }
    var qrCodeData by remember { mutableStateOf<String?>(null) }
    var hasNavigatedToPayment by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            getTransactionsUseCase = (context.applicationContext as App).appComponent.getTransactionsUseCase()
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                NavigationBarItem(
                    selected = selectedTab == "Home",
                    onClick = {
                        navController.navigate("home") { popUpTo(navController.graph.startDestinationId) { inclusive = true } }
                        selectedTab = "Home"
                        qrCodeData = null
                        hasNavigatedToPayment = false
                    },
                    icon = { Icon(painter = painterResource(R.drawable.ic_home), contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedTab == "Scan",
                    onClick = {
                        navController.navigate("scan") { popUpTo(navController.graph.startDestinationId) { inclusive = true } }
                        selectedTab = "Scan"
                        hasNavigatedToPayment = false
                    },
                    icon = { Icon(painter = painterResource(R.drawable.ic_camera), contentDescription = "Scan") },
                    label = { Text("Scan") }
                )
                NavigationBarItem(
                    selected = selectedTab == "Payment",
                    onClick = {
                        if (qrCodeData != null && !hasNavigatedToPayment) {
                            navController.navigate("payment") { popUpTo(navController.graph.startDestinationId) { inclusive = false } }
                            selectedTab = "Payment"
                            hasNavigatedToPayment = true
                        }
                    },
                    icon = { Icon(painter = painterResource(R.drawable.ic_payment), contentDescription = "Payment") },
                    label = { Text("Payment") }
                )
                NavigationBarItem(
                    selected = selectedTab == "History",
                    onClick = {
                        navController.navigate("history") { popUpTo(navController.graph.startDestinationId) { inclusive = true } }
                        selectedTab = "History"
                        hasNavigatedToPayment = false
                    },
                    icon = { Icon(painter = painterResource(R.drawable.ic_history), contentDescription = "History") },
                    label = { Text("History") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(
                    onScanClicked = {
                        navController.navigate("scan") { popUpTo(navController.graph.startDestinationId) { inclusive = true } }
                        selectedTab = "Scan"
                        hasNavigatedToPayment = false
                    },
                    onPayClicked = {
                        if (qrCodeData != null && !hasNavigatedToPayment) {
                            navController.navigate("payment") { popUpTo(navController.graph.startDestinationId) { inclusive = false } }
                            selectedTab = "Payment"
                            hasNavigatedToPayment = true
                        }
                    }
                )
            }
            composable("scan") {
                ScanScreen(
                    onQrCodeScanned = { qrData ->
                        qrCodeData = qrData
                        navController.navigate("payment") { popUpTo(navController.graph.startDestinationId) { inclusive = false } }
                        selectedTab = "Payment"
                        hasNavigatedToPayment = true
                    },
                    navController = navController
                )
            }
            composable("payment") { PaymentScreen(navController, qrCodeData) }
            composable("history") {
                val useCase = getTransactionsUseCase ?: return@composable
                HistoryScreen(useCase)
            }
        }
    }
}