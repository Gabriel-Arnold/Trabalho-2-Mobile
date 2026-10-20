package up.trabalho01.ui.theme.database

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity("Filmes")
data class Filmes (
    @PrimaryKey(true)
    val id: Int = 0,
    val nome: String,
    val desc: String
)
