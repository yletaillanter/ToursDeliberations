package com.ylt.toursdeliberations.repository

import com.ylt.toursdeliberations.model.Deliberation
import com.ylt.toursdeliberations.model.DeliberationsResponse
import com.ylt.toursdeliberations.model.Record
import com.ylt.toursdeliberations.service.DeliberationsService
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

    private fun getRemoteDeliberationById(delibId: String): Flow<DeliberationsResponse> = flow {
        Timber.d( "getRemoteDeliberationById()")

        try {
            val networkResult = deliberationsService.getDeliberation(delibId)
            emit(networkResult)
        } catch (throwable: Throwable) {
            Timber.e( "error while getting deliberation id [$delibId] : ${throwable}")
        }
    }

    fun getAllDeliberations(): Flow<List<Record>> = getRemoteDeliberations().map { it.records.toList()}

    fun getDeliberationById(delibId: String): Flow<List<Record>> = getRemoteDeliberationById(delibId).map { it.records.toList()}
}