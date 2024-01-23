package com.example.viewmodel.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewmodel.api.RetortClient
import com.example.viewmodel.data.items
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserModel : ViewModel() {
    val listUser = MutableLiveData<ArrayList<items>>()

    fun setUsers(query: String) {
        RetortClient.apiInstance
            .getUsers(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUser.postValue(response.body()?.items)
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }
            })
    }

    fun getUserSearch(): LiveData<ArrayList<items>> {
        return listUser
    }
}