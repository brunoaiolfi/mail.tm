package com.example.mailtm.modules.mail.views.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mailtm.modules.domain.repository.DomainRepository
import com.example.mailtm.modules.mail.repositories.MailAuthenticationRepository
import com.example.mailtm.modules.mail.services.MailAuthenticationService

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
    private val domainRepository = DomainRepository;
    private val _isLoading = MutableLiveData<Boolean>(false);
    val isLoading: LiveData<Boolean> = _isLoading;

    fun login(username: String, password: String) {
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
                TODO("Implement success callback")
            },
            {
                loginActivityProps.showToast(it);
            }
        );
    };
}