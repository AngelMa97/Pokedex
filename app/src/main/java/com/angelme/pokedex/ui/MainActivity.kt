package com.angelme.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.angelme.pokedex.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.angelme.pokedex.databinding.ActivityMainBinding
import com.angelme.pokedex.util.Generation


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
    }

    private fun setObservers() {
    }
}