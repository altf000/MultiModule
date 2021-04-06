package ru.altf000.multimodule.common_network.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.altf000.multimodule.common_entities.server.categories.CategoriesResultResponse
import ru.altf000.multimodule.common_entities.server.content.ContentResultResponse
import ru.altf000.multimodule.common_entities.server.content.FullContentResultResponse
import ru.altf000.multimodule.common_entities.server.countries.CountriesResponse
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

interface ApiService {

    @GET("categories/v6/")
    suspend fun getCategories(): RequestResult<CategoriesResultResponse>

    @GET("countries/v7")
    suspend fun getCountries(): RequestResult<CountriesResponse>

    @GET("collection/catalog/v7/")
    suspend fun getCollectionList(
        @Query("id") id: Int,
        @Query("from") from: Int,
        @Query("to") to: Int
    ): RequestResult<ContentResultResponse>

    @GET("videoinfo/v7/")
    suspend fun getMovie(@Query("id") id: Int): RequestResult<FullContentResultResponse>

    @GET("compilationinfo/v7/")
    suspend fun getSerial(@Query("id") id: Int): RequestResult<FullContentResultResponse>

    @GET("hydra/get/recommendation/v7/")
    suspend fun getRecommendations(
        @Query("id") id: Int,
        @Query("scenario_id") scenario: String
    ): RequestResult<ContentResultResponse>
}