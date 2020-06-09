package by.dro.app.translator.repository

import by.dro.app.translator.api.ApiService

class ApiRepository(private val service: ApiService) {

    suspend fun searchWords(query: String, page: Int, pageSize: Int) = service.searchWords(query, page, pageSize).await()

    suspend fun getMeanings(ids: String) = service.getMeanings(ids).await()
}