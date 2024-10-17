package com.example.mailtm.modules.auth.views.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mailtm.modules.auth.repositories.MailAuthenticationRepository
import com.example.mailtm.modules.auth.services.MailAuthenticationService

class LoginActivityViewModelFactory(
    private val application: Application,
    private val loginActivityProps: LoginActivityProps
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(application, loginActivityProps) as T
    }
}

class LoginViewModel(
    application: Application,
    private val loginActivityProps: LoginActivityProps
) : AndroidViewModel(application) {

    private val mailRepository = MailAuthenticationRepository;
    private val _isLoading = MutableLiveData(false);
    val isLoading: LiveData<Boolean> = _isLoading;

    fun login(username: String, password: String) {
        _isLoading.value = true;

        if (username.isEmpty() || password.isEmpty()) return loginActivityProps.showToast(
            "Please fill all fields"
        );

        val dto = MailAuthenticationService.MailRegisterAccountDTO(
            username,
            password
        );

        mailRepository.login(
            dto,
            {
                _isLoading.value = false;
                loginActivityProps.loginCallback(it);
            },
            {
                loginActivityProps.showToast(it);
                _isLoading.value = false;
            }
        );
    };
}