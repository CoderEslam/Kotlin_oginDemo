package net.simplifiedcoding.loginapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.simplifiedcoding.loginapp.R
import net.simplifiedcoding.loginapp.databinding.ActivityLoginBinding
import net.simplifiedcoding.loginapp.ui.home.HomeActivity
import net.simplifiedcoding.loginapp.util.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)


        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        binding.buttonSignIn.setOnClickListener {
            loginUser()
        }


    }

    private fun loginUser() {
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        if(email.isEmpty())
        {
            binding.rootLayout.snackbar("Please enter your Username")
        }
        else if(password.isEmpty())
        {
            binding.rootLayout.snackbar("Please enter your Password")
        }
        else
        {
            lifecycleScope.launch {
                try {
                    val authResponse = viewModel.userLogin(email, password)
                    if (authResponse.user != null) {
                        viewModel.saveLoggedInUser(authResponse.user)
                    } else {
                        binding.rootLayout.snackbar(authResponse.errorMessage!!)
                    }
                } catch (e: ApiException) {
                    e.printStackTrace()
                } catch (e: NoInternetException) {
                    e.printStackTrace()
                }
            }
        }


    }
}
