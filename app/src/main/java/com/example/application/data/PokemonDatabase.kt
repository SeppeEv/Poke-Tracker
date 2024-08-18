package com.example.application.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * An abstract class defining how to get the required data.
 */
@Database(entities = [FavoritePokemon::class], version = 1, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {

    //abstract fun pokemonDao(): PokemonDao
    abstract fun favoritePokemonDao(): FavoritePokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon_database")
                    .build().also { INSTANCE = it }
                    .also { INSTANCE = it }
            }
        }
    }
}