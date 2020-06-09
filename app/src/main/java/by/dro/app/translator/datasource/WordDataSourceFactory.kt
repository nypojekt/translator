package by.dro.app.translator.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import by.dro.app.translator.model.Word
import by.dro.app.translator.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope

class WordDataSourceFactory(
    private val repository: ApiRepository,
    private var query: String = "",
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Word>() {


    val source = MutableLiveData<WordDataSource>()

    override fun create(): DataSource<Int, Word> {
        val source = WordDataSource(repository, query, scope)
        this.source.postValue(source)
        return source
    }


    fun getQuery() = query

    fun getSource() = source.value

    fun updateQuery(query: String){
        this.query = query
        getSource()?.refresh()
    }
}