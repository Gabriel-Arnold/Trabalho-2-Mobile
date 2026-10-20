package up.trabalho01.ui.theme.aula.database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query

@Dao
interface FilmesDAO {

    @Insert
    fun inserir(filme: Filmes)

    @Query("SELECT * FROM filmes")
    fun buscarTodos() : List<Filmes>

}