package com.example.demodragger.di

import com.example.demodragger.MainActivity
import com.example.demodragger.MainModule
import com.example.demodragger.ui.login.LoginFragment
import com.example.demodragger.ui.login.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun provideLoginActivity(): LoginFragment
}