package com.example.demodragger

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.demodragger.base.BaseViewModel

class MainViewModel constructor(app: Application):BaseViewModel(app) {

    init {

        Log.d("TAG", "view model main activity called ")

    }
}