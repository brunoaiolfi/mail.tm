package com.example.mailtm

import DomainEntity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mailtm.modules.domain.repository.DomainRepository
import com.example.mailtm.modules.mail.repositories.MailAuthenticationRepository
import com.example.mailtm.modules.mail.services.MailAuthenticationService

class MainActivityViewModelFactory(
    private val application: Application,
    private val mainActivityProps: MainActivityProps
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(application, mainActivityProps) as T
    }
}

class MainActivityViewModel(
    application: Application,
    private val mainActivityProps: MainActivityProps
) : AndroidViewModel(application) {

    private val mailRepository = MailAuthenticationRepository;
    private val domainRepository = DomainRepository;
    private val _isLoading = MutableLiveData<Boolean>(false);
    val isLoading: LiveData<Boolean> = _isLoading;

    fun register(username: String, password: String, passwordConfirmation: String) {
        if (username.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) return mainActivityProps.showToast(
            "Please fill all fields"
        );
        if (password != passwordConfirmation) return mainActivityProps.showToast("Passwords do not match");
        if (username.contains("@")) return mainActivityProps.showToast("Username cannot contain '@'");

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
                        mainActivityProps.showToast(it);
                    }
                );
            },
            cbError = { message ->
                mainActivityProps.showToast(message);
            }
        );
    };
}