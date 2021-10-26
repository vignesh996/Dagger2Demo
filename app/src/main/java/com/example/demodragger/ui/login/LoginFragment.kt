package com.example.demodragger.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demodragger.BR
import com.example.demodragger.R
import com.example.demodragger.base.BaseFragment
import com.example.demodragger.databinding.FragmentLoginBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(), LoginViewModel.AuthResponse {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun getViewModel(): LoginViewModel? =
        ViewModelProvider(this,factory).get(LoginViewModel::class.java)

    override fun getBindingVariable(): Int =BR.loginViewModel

    override fun getContentView(): Int = R.layout.fragment_login

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate AuthResponse Interface
        getViewModel()?.setAuthResponse(this)
        // Login Button Listener
        onClickLoginBtn()


    }

    private fun onClickLoginBtn(){
        mDataBinding!!.loginBtn.setOnClickListener {
            // Getting User Inputs
            getViewModel()?.getUserData(mDataBinding!!)
        }
    }

    // Authentication Result CallBack
    override fun response() {
        getViewModel()?.toastMessage?.let { showToast(it) }

    }

}