package com.ravemaster.statesjetpackcompose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ravemaster.statesjetpackcompose.ui.theme.MyScreenStates

class MyViewModel: ViewModel() {
    val state = mutableStateOf(MyScreenStates())

    fun updateText( newText: String){
        state.value = state.value.copy(input = newText)
    }

    fun updateNamesList(newName: String){
        val currentList = state.value.nameList
        currentList.add(newName)
        state.value = state.value.copy(nameList = currentList)
    }
}