package com.example.mailtm.modules.auth.views.auth.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mailtm.modules.domain.repository.DomainRepository
import com.example.mailtm.modules.auth.repositories.MailAuthenticationRepository
import com.example.mailtm.modules.auth.services.MailAuthenticationService

class RegisterActivityViewModelFactory(
    private val application: Application,
    private val registerActivityProps: RegisterActivityProps
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterActivityViewModel(application, registerActivityProps) as T
    }
}

class RegisterActivityViewModel(
    application: Application,
    private val registerActivityProps: RegisterActivityProps
) : AndroidViewModel(application) {

    private val mailRepository = MailAuthenticationRepository;
    private val domainRepository = DomainRepository;

    private val _isLoading = MutableLiveData(false);
    val isLoading: LiveData<Boolean> = _isLoading;

    private val _domain = MutableLiveData("Loading ...");
    val domain: LiveData<String> = _domain;

    fun getDomain() {
        domainRepository.getDomains(
            cb = { domain ->
                _domain.value = "@${domain.members[0].domain}";
            },
            cbError = { message ->
                registerActivityProps.showToast(message);
            }
        );
    }

    fun register(username: String, password: String, passwordConfirmation: String) {
        _isLoading.value = true;

        if (username.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) return registerActivityProps.showToast(
            "Please fill all fields"
        );
        if (password != passwordConfirmation) return registerActivityProps.showToast("Passwords do not match");
        if (username.contains("@")) return registerActivityProps.showToast("Username cannot contain '@'");

        val dto = MailAuthenticationService.MailRegisterAccountDTO(
            "$username${domain.value}",
            password
        );

        mailRepository.register(
            dto,
            {
                registerActivityProps.showToast("Account created successfully");
                registerActivityProps.returnToLogin();
            },
            {
                registerActivityProps.showToast(it);
                _isLoading.value = false;
            }
        );
    };
};