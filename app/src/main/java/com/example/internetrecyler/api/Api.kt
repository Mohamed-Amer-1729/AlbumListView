package com.example.internetrecyler.api

import com.example.internetrecyler.data.Post
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/albums")
    suspend fun getPosts(): Response<List<Post>>

}