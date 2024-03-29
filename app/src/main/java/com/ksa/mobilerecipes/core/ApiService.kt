package com.ksa.mobilerecipes.core

import com.ksa.mobilerecipes.core.constants.Endpoints
import com.ksa.mobilerecipes.recipe_listing.model.CategoryList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/// Creates a retrofit builder
/// Using given baseURL and converter
private val retrofit =
    Retrofit.Builder().baseUrl(Endpoints.baseUrl).addConverterFactory(GsonConverterFactory.create())
        .build()

/// Creates a retrofit service by using above builder.
val recipeService = retrofit.create(ApiServices::class.java)

interface ApiServices {
    @GET(Endpoints.categories)
    suspend fun getCategories(): CategoryList {
       return CategoryList(emptyList())
    }
}