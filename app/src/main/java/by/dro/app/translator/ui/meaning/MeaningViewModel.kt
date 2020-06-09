package by.dro.app.translator.ui.meaning

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.dro.app.translator.api.NetworkState
import by.dro.app.translator.base.BaseViewModel
import by.dro.app.translator.datasource.WordDataSource
import by.dro.app.translator.model.Meaning
import by.dro.app.translator.repository.ApiRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MeaningViewModel(val repository: ApiRepository): BaseViewModel() {

    private val networkState = MutableLiveData<NetworkState>()
    private val meaningsLiveData = MutableLiveData<List<Meaning>>()
    private var supervisorJob = SupervisorJob()

    fun getMeaningsLiveData(): LiveData<List<Meaning>> = meaningsLiveData


    fun getMeanings(ids: String) {
        networkState.postValue(NetworkState.RUNNING)
        ioScope.launch(getJobErrorHandler() + supervisorJob) {
            delay(200)
            val meanings = repository.getMeanings(ids)
            networkState.postValue(NetworkState.SUCCESS)
            meaningsLiveData.postValue(meanings)

        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(WordDataSource::class.java.simpleName, "An error happened: $e")
        networkState.postValue(NetworkState.FAILED)
    }

}