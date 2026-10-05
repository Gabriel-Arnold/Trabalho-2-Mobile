package up.trabalho01.ui.theme.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.SwapHoriz

@Composable
fun NavBar(
    selectedItem: String,
    onHomeClick: () -> Unit,
    onPayClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    NavigationBar() {
        NavigationBarItem(
            selected = selectedItem == "home",
            onClick = onHomeClick,
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Início"
                )
            },
            label = { Text("Início") }
        )

        NavigationBarItem(
            selected = selectedItem == "clube",
            onClick = onPayClick,
            icon = {
                Icon(
                    Icons.Default.Camera,
                    contentDescription = "Clube"
                )
            },
            label = { Text("Clube") }
        )

        NavigationBarItem(
            selected = selectedItem == "caixinha",
            onClick = onMoreClick,
            icon = {
                Icon(
                    Icons.Default.MonetizationOn,
                    contentDescription = "Caixinha"
                )
            },
            label = { Text("Caixinha") }
        )
    }
}