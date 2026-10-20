package up.trabalho01

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import up.trabalho01.ui.theme.aula.database.AppDatabase
import up.trabalho01.ui.theme.aula.database.Filmes
import up.trabalho01.ui.theme.aula.database.FilmesDAO
import up.trabalho01.ui.theme.navigation.AppNavigation
import up.trabalho01.ui.theme.state.AppState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            //TelaUm()

            val appState = remember { AppState() }
            AppNavigation(appState)
        }
    }
}

@Composable
fun TelaUm(){

    var nome by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    var filmes by remember {
        mutableStateOf<List<Filmes>>(emptyList())
    }

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val filmesDao = db.filmesDAO()

    LaunchedEffect(Unit) {
        filmes = buscarFilmes(filmesDao)
        Log.d("Busca ok", "... ${filmes}")
    }

    Column() {
        TextField(value = nome,
            onValueChange = { nome = it},
            label = { Text("Nome") } )

        TextField(value = desc,
            onValueChange = { desc = it},
            label = { Text("Descricao") } )

        Button(
            onClick = {

                CoroutineScope(Dispatchers.IO).launch {
                    try{
                        filmesDao.inserir(Filmes(0, nome, desc))
                    }catch (e: Exception ){
                        Log.e("Erro do Add", "Msg: ${e.message}")
                    }
                }

            }
        ) { Text("Add") }
    }

    LazyColumn {
        items(filmes){
                filme -> Text(filme.nome)
        }
    }

}


suspend fun buscarFilmes(filmesDao: FilmesDAO): List<Filmes>{
    return withContext(Dispatchers.IO){
        try {
            filmesDao.buscarTodos()
        } catch (e: Exception){
            Log.e("Erro ao buscar", "${e.message}")
            emptyList()
        }
    }
}
