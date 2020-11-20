package com.ylt.toursdeliberations

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.ylt.toursdeliberations.repository.DeliberationsRepository
import com.ylt.toursdeliberations.service.DeliberationsService
import com.ylt.toursdeliberations.ui.main.MainViewModelFactory

object Injection {
    private fun provideDeliberationRepository(context: Context): DeliberationsRepository {
        return DeliberationsRepository(DeliberationsService.create())
    }

    fun provideMainViewModelFactory(context: Context): ViewModelProvider.Factory {
        return MainViewModelFactory(provideDeliberationRepository(context))
    }
}