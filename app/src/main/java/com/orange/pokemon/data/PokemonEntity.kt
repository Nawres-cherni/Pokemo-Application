package com.orange.pokemon.data

import androidx.room.*

@Entity(tableName = "table_de_pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val Id: Int = 0,

    val name: String,
    val speed: Int,
    val image: String


    )