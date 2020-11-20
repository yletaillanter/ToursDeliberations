package com.ylt.toursdeliberations.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ylt.toursdeliberations.repository.DeliberationsRepository

class MainViewModelFactory (private val deliberatiobRepo: DeliberationsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(deliberatiobRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}