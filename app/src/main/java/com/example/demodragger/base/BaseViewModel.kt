package com.example.demodragger.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel(app : Application) : AndroidViewModel(app){

    var toastMessage: String = ""
}