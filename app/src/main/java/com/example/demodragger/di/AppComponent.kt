package com.example.demodragger.di

import android.app.Application
import com.example.demodragger.MyApplication
import com.example.demodragger.di.modules.ApiModule
import com.example.demodragger.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class,AppModule::class,ApiModule::class,ActivityBuilder::class]
)
interface AppComponent {

    /* We will call this builder interface from our custom Application class.
* This will set our application object to the AppComponent.
* So inside the AppComponent the application instance is available.
* So this application instance can be accessed by our modules
 */
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    /*  This is our custom Application class*/
    fun inject(application: MyApplication)
}