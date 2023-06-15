package org.d3if3135.mobpro1.ui.daftar

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3135.mobpro1.model.NamaOrang
import org.d3if3135.mobpro1.network.NamaOrangApi
import org.d3if3135.mobpro1.network.UpdateWorker
import java.util.concurrent.TimeUnit

class DaftarViewModel : ViewModel() {

    private val data = MutableLiveData<List<NamaOrang>>()
    private val status = MutableLiveData<NamaOrangApi.ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(NamaOrangApi.ApiStatus.LOADING)
            try {
                data.postValue(NamaOrangApi.service.getNamaOrang())
                status.postValue(NamaOrangApi.ApiStatus.SUCCESS)
            } catch (e: java.lang.Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(NamaOrangApi.ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<NamaOrang>> = data
    fun getStatus(): LiveData<NamaOrangApi.ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}