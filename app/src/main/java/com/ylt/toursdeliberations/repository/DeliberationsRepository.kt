package com.ylt.toursdeliberations.repository

import android.util.Log
import com.ylt.toursdeliberations.model.Deliberations
import com.ylt.toursdeliberations.model.DeliberationsResponse
import com.ylt.toursdeliberations.model.Record
import com.ylt.toursdeliberations.service.DeliberationsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber

class DeliberationsRepository(private val deliberationsService: DeliberationsService) {

    private fun getRemoteDeliberations(): Flow<DeliberationsResponse> = flow {
        Timber.d( "getRemoteDeliberations()")

        try {
            val networkResult = deliberationsService.getDeliberations()
            emit(networkResult)
        } catch (throwable: Throwable) {
            Timber.e( "error while getting All deliberations: ${throwable}")
        }
    }

    fun getAllDeliberations(): Flow<List<Record>> = getRemoteDeliberations().map { it -> it.records.toList()}
}