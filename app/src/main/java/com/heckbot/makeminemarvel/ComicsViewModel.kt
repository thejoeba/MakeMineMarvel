package com.heckbot.makeminemarvel

import android.os.Parcelable
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heckbot.model.Comic

class ComicsViewModel: ViewModel() {
    enum class ViewState {
        List, Details
    }

    private val _comicsMutableLiveData = MutableLiveData<List<Comic>>()
    val comicsLiveData: LiveData<List<Comic>> = _comicsMutableLiveData
    private val _selectedComicMutableLiveData = MutableLiveData<Comic?>()
    val selectedComicLiveData: LiveData<Comic?> = _selectedComicMutableLiveData
    private val _viewStateMediatorLiveData = MediatorLiveData<ViewState>().apply {
        value = ViewState.List
        addSource(selectedComicLiveData) {
            val newState = calculateViewState()
            if (value != newState) {
                value = newState
            }
        }
    }
    val viewStateLiveData: LiveData<ViewState> = _viewStateMediatorLiveData

    var recyclerViewState: Parcelable? = null

    init {
        Dependencies.comicsRepository.subscribeToComicsList(hashCode().toString()) {
            _comicsMutableLiveData.postValue(it)
        }
    }

    fun calculateViewState(): ViewState {
        return selectedComicLiveData.value?.let { ViewState.Details } ?: ViewState.List
    }

    fun comicSelected(position: Int) {
        _selectedComicMutableLiveData.value = comicsLiveData.value?.getOrNull(position)
    }

    fun comicCleared() {
        _selectedComicMutableLiveData.value = null
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public override fun onCleared() {
        Dependencies.comicsRepository.unsubscribeToComicList(hashCode().toString())
        super.onCleared()
    }
}