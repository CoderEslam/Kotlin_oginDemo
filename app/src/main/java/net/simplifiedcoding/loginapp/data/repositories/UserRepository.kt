package net.simplifiedcoding.loginapp.data.repositories

import net.simplifiedcoding.loginapp.data.db.AppDatabase
import net.simplifiedcoding.loginapp.data.db.entities.User
import net.simplifiedcoding.loginapp.data.network.MyApi
import net.simplifiedcoding.loginapp.data.network.SafeApiRequest
import net.simplifiedcoding.loginapp.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }



    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()

}