package com.example.demodragger

import android.app.Activity
import android.app.Application
import com.example.demodragger.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MyApplication: Application(),HasActivityInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        // Instantiate Dagger Component
        DaggerAppComponent.builder().application(this).build().inject(this)

    }

    // Method for Activity Injection
    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }


}