package com.example.demodragger.ui.login

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.demodragger.db.UserDao
import com.example.demodragger.helper.ViewModelProviderFactory
import com.example.demodragger.network.ApiStories
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// This Module Provides All Login Fragment Injection Objects
@Module
class LoginModule {


    // Provides Login ViewModel Factory
    @Provides
    fun provideViewModelProvider(viewModel: LoginViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}