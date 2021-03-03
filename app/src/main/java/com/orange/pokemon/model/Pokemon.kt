package com.orange.pokemon.model


import com.google.gson.annotations.SerializedName

data class Pokemon(

    val abilities: List<String>,
    val attack: Int,
    @SerializedName("base_exp")
    val baseExp: String,
    val category: String,
    val cycles: String,
    val defense: Int,
    @SerializedName("egg_groups")
    val eggGroups: String,
    val evolutions: List<String>,
    val evolvedfrom: String,
    @SerializedName("female_percentage")
    val femalePercentage: String,
    val genderless: Int,
    val height: String,
    val hp: Int,
    val id: String,
    val imageurl: String,
    @SerializedName("male_percentage")
    val malePercentage: String,
    val name: String,
    val reason: String,
    @SerializedName("special_attack")
    val specialAttack: Int,
    @SerializedName("special_defense")
    val specialDefense: Int,
    val speed: Int,
    val total: Int,
    val typeofpokemon: List<String>,
    val weaknesses: List<String>,
    val weight: String,
    val xdescription: String,
    val ydescription: String
)