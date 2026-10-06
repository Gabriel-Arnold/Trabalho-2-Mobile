package up.trabalho01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import up.trabalho01.ui.theme.navigation.AppNavigation
import up.trabalho01.ui.theme.state.AppState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = remember { AppState() }
            AppNavigation(appState)
        }
    }
}
