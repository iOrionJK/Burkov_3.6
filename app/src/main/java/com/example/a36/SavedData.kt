package com.example.a36

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class SavedData: ViewModel() {
    val bgColor : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val scrollPosition : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}