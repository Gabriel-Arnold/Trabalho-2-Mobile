package up.trabalho01.ui.theme.aula.database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase

@Database(entities = [Filmes::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // public static AppDatabase INSTANCE = null
    abstract fun filmesDAO(): FilmesDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{

            val temInstance = INSTANCE

            if(temInstance != null){
                return temInstance
            }else{

                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }

            }

        }


    }

}