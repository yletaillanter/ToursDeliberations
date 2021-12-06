package com.ylt.toursdeliberations.repository

import com.ylt.toursdeliberations.model.DeliberationsResponse
import com.ylt.toursdeliberations.model.Record
import com.ylt.toursdeliberations.service.DeliberationsService
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.time.LocalDate

class DeliberationsRepository(private val deliberationsService: DeliberationsService) {

    fun getAllDeliberations(): Flow<List<Record>> = getRemoteDeliberations().map { it.records.toList().filter { record -> LocalDate.parse(record.deliberation.delibDate).isAfter(LocalDate.parse("2020-01-01")) }}
    fun getDeliberationById(delibId: String): Flow<List<Record>> = getRemoteDeliberationById(delibId).map { it.records.toList() }

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
        Timber.d( "getRemoteDeliberationById($delibId)")

        try {
            val networkResult = deliberationsService.getDeliberation(delibId)
            emit(networkResult)
        } catch (throwable: Throwable) {
            Timber.e( "error while getting deliberation by id [$delibId] : ${throwable}")
        }
    }

    private fun getRemoteDeliberationsByDate(delibDate: String): Flow<DeliberationsResponse> = flow {
        Timber.d("getRemoteDeliberationsByDate($delibDate")

        try {
            val networkResult = deliberationsService.getDeliberationsByDate(delibDate)
            emit(networkResult)
        } catch (throwable: Throwable) {
            Timber.e( "error while getting deliberation by date [$delibDate] : ${throwable}")
        }
    }
}