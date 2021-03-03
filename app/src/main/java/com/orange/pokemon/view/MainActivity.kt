package com.orange.pokemon.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.orange.pokemon.adapter.PokemonAdapter
import com.orange.pokemon.data.PokemonDao
import com.orange.pokemon.data.PokemonEntity
import com.orange.pokemon.data.pokemonDataBase
import com.orange.pokemon.databinding.ActivityMainBinding
import com.orange.pokemon.model.Pokemon
import com.orange.pokemon.networking.ApiService
import com.orange.pokemon.networking.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var database: pokemonDataBase
    private lateinit var dao: PokemonDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonAdapter = PokemonAdapter()
        val service = NetworkClient().getRetrofit().create(ApiService::class.java)
        service.getAllPokemons().enqueue(object : Callback<List<Pokemon>> {
            override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse: ${response.body()?.get(0)}")
                    val listPokemon : List<Pokemon>? = response.body()
                    database = pokemonDataBase.getInstance(this@MainActivity)
                    dao = database.getPokemon()


                    GlobalScope.launch(Dispatchers.Main) {
                        dao.insertAll(
                            listPokemon!!.map {
                                PokemonEntity(
                                    name = it.name,
                                    speed =  it.speed,
                                    image = it.imageurl
                                )
                            }
                        )
                        val list = dao.getAll()
                        pokemonAdapter.submitList(list)
                        binding.recyclerview.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = pokemonAdapter
                        }
                    }


                }
            }






            override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()

            }

        })
    }
}