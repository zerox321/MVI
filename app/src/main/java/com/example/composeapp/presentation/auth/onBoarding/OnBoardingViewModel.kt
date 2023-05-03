package com.example.composeapp.presentation.auth.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor():ViewModel(){
    val items = listOf(OnBoardingItem.FirstPage, OnBoardingItem.SecondPage, OnBoardingItem.ThirdPage)
    val itemsCount=items.size
    fun isLastIndex (index:Int) = index < itemsCount-1
    private val _selectedIndex= MutableStateFlow<Int>(0)
    val selectedIndex get() =  _selectedIndex.asStateFlow()
    fun setSelectedIndex(value:Int)=viewModelScope.launch {_selectedIndex.emit(value)  }
}