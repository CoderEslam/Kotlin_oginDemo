package net.simplifiedcoding.loginapp.ui.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.simplifiedcoding.loginapp.data.db.entities.User
import net.simplifiedcoding.loginapp.data.repositories.UserRepository


class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    fun getLoggedInUser() = repository.getUser()

    suspend fun userLogin(
        email: String,
        password: String
    ) = withContext(Dispatchers.IO) { repository.userLogin(email, password) }


    suspend fun saveLoggedInUser(user: User) = repository.saveUser(user)

}