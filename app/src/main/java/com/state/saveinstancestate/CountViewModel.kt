package com.state.saveinstancestate

import androidx.lifecycle.ViewModel

class CountViewModel :ViewModel() {

    var number = 0
    fun addOne(){
        number++
    }
}