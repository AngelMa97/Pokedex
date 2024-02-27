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
import com.angelme.pokedex.ui.pokedex.PokedexViewModel
import com.google.android.material.snackbar.Snackbar


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
        binding.apply {
            progressMainIndicator.hide()
            bottomNavigationView.setupWithNavController(navController)
            topAppBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.logout -> {
                        viewModel.signOut()
                        true
                    }
                    R.id.sync -> {
                        viewModel.syncStoredPokemon()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.uiState.observe(this@MainActivity) {
            when(it) {
                is WorkResult.Success -> {
                    binding.progressMainIndicator.hide()
                    if (it.data == null) {
                        finish()
                    }
                }
                is WorkResult.Error -> {
                    binding.progressMainIndicator.hide()
                    Snackbar.make(binding.root, it.exception.message ?: "Unknown error", Snackbar.LENGTH_SHORT).show()
                }
                is WorkResult.Loading -> binding.progressMainIndicator.show()
            }
        }
    }
}