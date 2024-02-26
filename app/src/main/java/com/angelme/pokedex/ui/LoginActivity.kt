package com.angelme.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.angelme.pokedex.R
import com.angelme.pokedex.databinding.ActivityLoginBinding
import com.angelme.pokedex.repository.WorkResult
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSignInView()
        setObservers()
    }

    private fun setUpSignInView() {
        binding.apply {
            btnLogin.text = getString(R.string.log_in)
            btnLogin.setOnClickListener {
                viewModel.signInWithEmailAndPassword(
                    textFieldEmail.editText?.text.toString(),
                    textFieldPassword.editText?.text.toString()
                )
            }
            btnCancel.visibility = View.GONE
            signUpLabel.visibility = View.VISIBLE
            signUpLabel.setOnClickListener {
                setUpSignUpView()
            }
            loadingLoginView.hide()
        }
    }

    private fun setUpSignUpView() {
        binding.apply {
            btnLogin.text = getString(R.string.sign_up)
            btnLogin.setOnClickListener {
                viewModel.signUpWitEmailAndPassword(
                    textFieldEmail.editText?.text.toString(),
                    textFieldPassword.editText?.text.toString()
                )
            }
            btnCancel.visibility = View.VISIBLE
            signUpLabel.visibility = View.GONE
            btnCancel.setOnClickListener {
                setUpSignInView()
            }
            loadingLoginView.hide()
        }
    }

    private fun setObservers() {
        binding.apply {
            viewModel.uiState.observe(this@LoginActivity) {
                when (it) {
                    is WorkResult.Error -> {
                        loadingLoginView.hide()
                        Snackbar.make(
                            binding.root,
                            it.exception.message ?: "Unknown error",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }

                    WorkResult.Loading -> loadingLoginView.show()
                    is WorkResult.Success -> {
                        binding.apply {
                            loadingLoginView.hide()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        }
                    }
                }
            }
        }
    }
}