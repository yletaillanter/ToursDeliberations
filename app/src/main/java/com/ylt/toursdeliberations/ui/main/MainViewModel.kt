package com.ylt.toursdeliberations.ui.main

import androidx.lifecycle.*
import com.ylt.toursdeliberations.model.Record
import com.ylt.toursdeliberations.repository.DeliberationsRepository
import timber.log.Timber

class MainViewModel(private val deliberationRepo: DeliberationsRepository) : ViewModel() {
    val deliberationsList: LiveData<List<Record>> = deliberationRepo.getAllDeliberations().asLiveData(viewModelScope.coroutineContext)

    fun deliberation(delibId: String): LiveData<List<Record>> {
        return deliberationRepo.getDeliberationById(delibId).asLiveData(viewModelScope.coroutineContext)
    }
}