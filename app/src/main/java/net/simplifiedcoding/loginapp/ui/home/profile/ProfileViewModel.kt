package net.simplifiedcoding.loginapp.ui.home.profile

import androidx.lifecycle.ViewModel;
import net.simplifiedcoding.loginapp.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()

}
