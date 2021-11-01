package com.example.demodragger.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.demodragger.BR
import com.example.demodragger.R
import com.example.demodragger.base.BaseFragment
import com.example.demodragger.databinding.FragmentLoginBinding
import com.example.mydatabaselibrary.db.entities.LogNotes
import dagger.android.support.AndroidSupportInjection
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


open class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(), LoginViewModel.AuthResponse {

    // ViewModel Factory Injection
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    // viewModel
    override fun getViewModel(): LoginViewModel? =
        ViewModelProvider(this, factory).get(LoginViewModel::class.java)

    // Binding Variable
    override fun getBindingVariable(): Int =BR.loginViewModel

    // Attach Xml file
    override fun getContentView(): Int = R.layout.fragment_login

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Authentication Response Interface Instantiation
        getViewModel()?.setAuthResponse(this)

        onClickLoginBtn()

    }

    // Login Button Listener
    private fun onClickLoginBtn(){
        mDataBinding!!.loginBtn.setOnClickListener {
            getViewModel()?.getUserData(mDataBinding!!)
        }
    }

    // Authentication Response CallBack
    override fun response() {
        getViewModel()?.toastMessage?.let { showToast(it) }

        // Printing Log Data's inside the Logcat
        getViewModel()?.getLogNotes()

    }

}