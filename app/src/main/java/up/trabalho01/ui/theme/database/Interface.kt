package up.trabalho01.ui.theme.database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query

@Dao
interface FimesDAO {

    @Insert
    fun inserir(filme: Filmes)

    @Query("SELECT * FROM Filmes")
    fun buscarTodos() : List<Filmes>
}