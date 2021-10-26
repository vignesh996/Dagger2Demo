package com.example.demodragger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.demodragger.base.BaseActivity
import com.example.demodragger.databinding.ActivityMainBinding
import com.example.demodragger.db.AppDataBase
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    /*Inject fragment object.*/
    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun getContentView(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel? =
        ViewModelProvider(this).get(MainViewModel::class.java)

    override fun getBindingVariable(): Int = BR.mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

    }

}