package com.example.demodragger.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.demodragger.base.BaseViewModel
import com.example.demodragger.databinding.FragmentLoginBinding
import com.example.demodragger.db.entities.User
import com.example.mydatabaselibrary.db.entities.LogNotes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository,
                                         app: Application): BaseViewModel(app) {

    var userName = MutableLiveData<String>()
    var password= MutableLiveData<String>()
    private var authResponse: AuthResponse? = null
    private var classDetails =Throwable().stackTrace

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
            var note =LogNotes(getDate(), getTime(), "warning",
                    classDetails[0].fileName, classDetails[0].methodName, classDetails[0].lineNumber.toString())
            insertLog(note)
        }else{
            toastMessage = "Login Successfully"
            var note =LogNotes(getDate(), getTime(), "warning",
                    classDetails[0].fileName, classDetails[0].methodName, classDetails[0].lineNumber.toString())
            insertLog(note)
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

    // Inserting Log Data
    private fun insertLog(log: LogNotes) {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.insertLog(log)
            Log.d("TAG", "insertLog: successfully")
        }
    }

    //  Fetch All Logs from LogNoteDatabase
    fun getLogNotes() {
        viewModelScope.launch(Dispatchers.Main) {
            var logs= getAllLog()
            for(log in logs){
                Log.d("TAG", "insertLog: $log")
            }
        }

    }

    // Fetching All Logs from LogNoteDatabase
    private suspend fun getAllLog(): List<LogNotes>{
        return withContext(Dispatchers.IO){
            return@withContext loginRepository.getAllLog()
        }
    }

    // Method for Current Time
    private fun getTime(): String {
        val sdfForDate = SimpleDateFormat("HH:mm:ss")
        val currentTime: String = sdfForDate.format(Date())
        Log.d("TAG", "onViewCreated: $currentTime")
        return currentTime
    }

    //Method for Current Date
    private fun  getDate() : String {
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        val currentDate: String = sdf.format(Date())
        Log.d("TAG", "onViewCreated: $currentDate")
        return currentDate
    }

}