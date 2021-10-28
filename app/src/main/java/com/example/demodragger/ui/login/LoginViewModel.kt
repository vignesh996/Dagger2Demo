package com.example.demodragger.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.demodragger.base.BaseViewModel
import com.example.demodragger.databinding.FragmentLoginBinding
import com.example.demodragger.db.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository,
                                         app: Application): BaseViewModel(app) {

    var userName = MutableLiveData<String>()
    var password= MutableLiveData<String>()
    private var authResponse: AuthResponse? = null

    init {
        Log.d("TAG", "view model login called ")
    }

    // Getting User Inputs
    fun getUserData(mDataBinding: FragmentLoginBinding) {
        var name = userName.value
        var password = password.value

        userInputValidation(name,password,mDataBinding)
    }

    //User Inputs Validation
    private fun userInputValidation(name: String?, password: String?, mDataBinding: FragmentLoginBinding) {
        if (!name.isNullOrEmpty()){
            if (!password.isNullOrEmpty()){
                if (password.length >3){
                        userAuth(name,password)
                }else{
                    mDataBinding.etPass.error ="Password Wrong!"
                }
            }else{
                mDataBinding.etPass.error =" Required Password!"
            }
        }else{
            mDataBinding.etUser.error = "Required User Name!"
        }
    }

    // User Authentication Method
    private fun userAuth(name: String, password: String) {
        viewModelScope.launch(Dispatchers.Main) {
            var user  = authenticate(name, password)
            authResult(user)
        }
    }

    // Authentication from Database
    private suspend fun authenticate(name: String, password: String): User{
        return withContext(Dispatchers.IO){
            return@withContext loginRepository.userAuth(name,password)
        }
    }

    // Set Authentication Result
    private fun authResult(user : User){
        if (user ==  null){
            toastMessage = "Invalid User Data"
        }else{
            toastMessage = "Login Successfully"
        }
        authResponse?.response()
    }

    //Method for initializing Response Interface
    fun setAuthResponse(authResponse : AuthResponse){
        this.authResponse = authResponse
    }

    // Authentication Response CallBack Interface
    interface AuthResponse{
        fun response()
    }

}