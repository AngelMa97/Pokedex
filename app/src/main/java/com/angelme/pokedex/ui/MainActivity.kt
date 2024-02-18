package com.angelme.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.angelme.pokedex.R
import com.angelme.pokedex.databinding.ActivityMainBinding
import com.angelme.pokedex.repository.WorkResult
import com.angelme.pokedex.ui.mypokemon.MyPokemonViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViews()
        setObservers()
    }

    private fun setViews() {
        val navController = this.findNavController(R.id.navigation_host)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun setObservers() {
        viewModel.iuState.observe(this@MainActivity) {
            when(it) {
                is WorkResult.Loading -> binding.progressIndicator.show()
                else -> binding.progressIndicator.hide()
            }
        }
    }
}