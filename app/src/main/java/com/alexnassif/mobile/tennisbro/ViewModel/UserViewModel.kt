package com.alexnassif.mobile.tennisbro.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexnassif.mobile.tennisbro.Model.Player
import com.alexnassif.mobile.tennisbro.Repository.DataService

class UserViewModel: ViewModel() {

    private val userList: MutableLiveData<List<Player>> = MutableLiveData()

    fun getUserList(): MutableLiveData<List<Player>> {

        if(userList.value == null){
            DataService.getAllUsers {
                userList.postValue(it)
            }
        }

        return userList
    }
}