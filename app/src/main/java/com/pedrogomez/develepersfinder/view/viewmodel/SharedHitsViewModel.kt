package com.pedrogomez.develepersfinder.view.viewmodel

import androidx.lifecycle.*
import com.pedrogomez.develepersfinder.contracts.ManagerContracts
import com.pedrogomez.develepersfinder.models.db.UserPicture
import com.pedrogomez.develepersfinder.models.result.Result
import kotlinx.coroutines.*

class SharedHitsViewModel(
    private val dataBase: ManagerContracts.DataBase
) : ViewModel(){

    private val _hitsListLiveData : LiveData<List<UserPicture>> = dataBase.observeFavorites()

    val hitsListLiveData = _hitsListLiveData

    val selectedHitLiveData = MutableLiveData<UserPicture>()

    val loaderData = MutableLiveData<Result<Boolean>>()

    fun reloadContent(){
        setLoadingState()
        viewModelScope.launch {
            setSuccessState()
        }
    }

    fun setLoadingState(){
        loaderData.postValue(
            Result.Loading
        )
    }

    fun setSuccessState(){
        loaderData.postValue(
            Result.Success(true)
        )
    }

    fun loadFirstPage(){
        loadMore(0)
    }

    fun loadMore(page:Int){
        setLoadingState()
        viewModelScope.launch {
            setSuccessState()
        }
    }

    fun saveSelected(productItem: UserPicture){
        selectedHitLiveData.value = productItem
    }

    fun delete(hitItem: UserPicture) {
        viewModelScope.launch {
            dataBase.delete(hitItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}