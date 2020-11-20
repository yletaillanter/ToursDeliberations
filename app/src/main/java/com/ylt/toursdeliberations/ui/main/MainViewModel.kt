package com.ylt.toursdeliberations.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ylt.toursdeliberations.model.Record
import com.ylt.toursdeliberations.repository.DeliberationsRepository

class MainViewModel(private val deliberationRepo: DeliberationsRepository) : ViewModel(){
    val deliberationsList: LiveData<List<Record>> = deliberationRepo.getAllDeliberations().asLiveData(viewModelScope.coroutineContext)
}