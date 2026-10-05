package up.trabalho01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import up.trabalho01.ui.theme.components.NavBar
import up.trabalho01.ui.theme.screens.caixinha
import up.trabalho01.ui.theme.screens.cartao
import up.trabalho01.ui.theme.screens.clube
import up.trabalho01.ui.theme.screens.home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            mainScreen()
        }
    }
}

@Composable
fun mainScreen() {

    var selectedScreen by remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = { NavBar(
            selectedItem = selectedScreen,
            onHomeClick = { selectedScreen = "home"},
            onPayClick = { selectedScreen = "clube"},
            onMoreClick = { selectedScreen = "caixinha"}
        ) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedScreen) {
                "home" -> home()
                "clube" -> cartao()
                "caixinha" -> caixinha()
            }
        }
    }
}