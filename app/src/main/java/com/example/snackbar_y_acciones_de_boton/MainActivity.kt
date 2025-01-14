package com.example.snackbar_y_acciones_de_boton

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snackbar_y_acciones_de_boton.ui.theme.Snackbar_y_Acciones_de_BotonTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Snackbar_y_Acciones_de_BotonTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Se realizó una acción",
                        actionLabel = "Deshacer",
                        duration = SnackbarDuration.Short
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        Toast.makeText(context, "Acción deshecha", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text(text = "Mostrar Snackbar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Snackbar_y_Acciones_de_BotonTheme {
        MainScreen()
    }
}
