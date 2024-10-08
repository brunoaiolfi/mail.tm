package com.example.mailtm.modules.mail.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mailtm.MainActivityViewModel
import com.example.mailtm.modules.domain.repository.DomainRepository
import com.example.mailtm.modules.mail.repositories.MailAuthenticationRepository
import com.example.mailtm.modules.mail.services.MailAuthenticationService

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
    private val _isLoading = MutableLiveData<Boolean>(false);
    val isLoading: LiveData<Boolean> = _isLoading;

    fun register(username: String, password: String, passwordConfirmation: String) {
        if (username.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) return registerActivityProps.showToast(
            "Please fill all fields"
        );
        if (password != passwordConfirmation) return registerActivityProps.showToast("Passwords do not match");
        if (username.contains("@")) return registerActivityProps.showToast("Username cannot contain '@'");

        domainRepository.getDomains(
            cb = { domain ->
                val dto = MailAuthenticationService.MailRegisterAccountDTO(
                    "$username@${domain.members[0].domain}",
                    password
                );

                mailRepository.register(
                    dto,
                    {
                        TODO("Implement success callback")
                    },
                    {
                        registerActivityProps.showToast(it);
                    }
                );
            },
            cbError = { message ->
                registerActivityProps.showToast(message);
            }
        );
    };
}