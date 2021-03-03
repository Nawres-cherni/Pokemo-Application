package com.orange.pokemon.data

import androidx.room.*

@Dao
interface PokemonDao {

    @Insert
    suspend fun insertOne(entity: PokemonEntity)

    @Insert
    suspend fun insertAll(entities: List<PokemonEntity>)

    @Delete
    suspend fun deleteOne(entity: PokemonEntity)

    @Query("SELECT * FROM table_de_pokemon")
    suspend fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM table_de_pokemon WHERE id = :id")
    suspend fun getOneByID(id: Int): PokemonEntity

    @Update
    suspend fun updateOne(transactionEntity: PokemonEntity)

}