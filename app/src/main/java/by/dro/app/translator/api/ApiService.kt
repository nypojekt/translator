package by.dro.app.translator.api

import by.dro.app.translator.model.Meaning
import by.dro.app.translator.model.Word
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("meanings")
    fun getMeanings(
        @Query("ids") ids: String
    ): Deferred<List<Meaning>>

    @GET("words/search")
    fun searchWords(
        @Query("search") search: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Deferred<List<Word>>

}