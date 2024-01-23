package com.example.viewmodel.api

import com.example.viewmodel.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiConfig {

   @GET("search/users")
   fun getUsers(
       @Query("q") query: String
   ): Call<UserResponse>

}