package ru.altf000.multimodule.common_network.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.altf000.multimodule.common_entities.network.categories.CategoriesResultNetwork
import ru.altf000.multimodule.common_entities.network.content.ContentResultNetwork
import ru.altf000.multimodule.common_entities.network.content.FullContentResultNetwork
import ru.altf000.multimodule.common_entities.network.countries.CountriesResultNetwork
import ru.altf000.multimodule.common_entities.network.pages.PagesResultNetwork
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

interface ApiService {

    @GET("categories/v6/")
    suspend fun getCategories(): RequestResult<CategoriesResultNetwork>

    @GET("countries/v7")
    suspend fun getCountries(): RequestResult<CountriesResultNetwork>

    @GET("collection/catalog/v7/")
    suspend fun getCollectionList(
        @Query("id") id: Int,
        @Query("from") from: Int,
        @Query("to") to: Int
    ): RequestResult<ContentResultNetwork>

    @GET("videoinfo/v7/")
    suspend fun getMovie(@Query("id") id: Int): RequestResult<FullContentResultNetwork>

    @GET("compilationinfo/v7/")
    suspend fun getSerial(@Query("id") id: Int): RequestResult<FullContentResultNetwork>

    @GET("hydra/get/recommendation/v7/")
    suspend fun getRecommendations(
        @Query("id") id: Int,
        @Query("scenario_id") scenario: String
    ): RequestResult<ContentResultNetwork>

    @GET("pages/v5?id=1&width=5")
    suspend fun getPages(): RequestResult<PagesResultNetwork>
}