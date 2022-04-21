package com.ylt.toursdeliberations.ui.main

import androidx.lifecycle.*
import com.ylt.toursdeliberations.model.Record
import com.ylt.toursdeliberations.repository.DeliberationsRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val deliberationRepo: DeliberationsRepository) : ViewModel() {

    val deliberationsList: Flow<List<Record>> = deliberationRepo.getAllDeliberations()
    fun deliberationsListByDate(delibDate: String): Flow<List<Record>> { return deliberationRepo.getDeliberationsByDate(delibDate) }

    fun deliberation(delibId: String): Flow<List<Record>> { return deliberationRepo.getDeliberationById(delibId) }
}