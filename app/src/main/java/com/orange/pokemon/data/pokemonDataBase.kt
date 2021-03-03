package com.orange.pokemon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [PokemonEntity::class],
    version = 1
)
abstract class pokemonDataBase : RoomDatabase() {

    abstract fun getPokemon(): PokemonDao


    companion object {
        private var instance: pokemonDataBase? = null
        fun getInstance(context: Context): pokemonDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    pokemonDataBase::class.java,
                    "pokemon_database"
                ).build()
            }
            return instance!!
        }
    }
}