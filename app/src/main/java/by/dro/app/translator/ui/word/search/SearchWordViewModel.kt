package by.dro.app.translator.ui.word.search


import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import by.dro.app.translator.api.NetworkState
import by.dro.app.translator.base.BaseViewModel

import by.dro.app.translator.datasource.WordDataSourceFactory
import by.dro.app.translator.repository.ApiRepository


class SearchWordViewModel(repository: ApiRepository): BaseViewModel() {


    private val wordDataSource = WordDataSourceFactory(repository = repository, scope = ioScope)


    val words = LivePagedListBuilder(wordDataSource, pagedListConfig()).build()
    val networkState: LiveData<NetworkState>? = switchMap(wordDataSource.source) { it.getNetworkState() }


    fun searchWords(query: String) {
        val search = query.trim()
        if (wordDataSource.getQuery() == search) return
        wordDataSource.updateQuery(search)
    }


    fun refreshFailedRequest() =
        wordDataSource.getSource()?.retryFailedQuery()



    fun refreshAllList() =
        wordDataSource.getSource()?.refresh()


    fun getCurrentQuery() =
        wordDataSource.getQuery()


    private fun pagedListConfig() = PagedList.Config.Builder()
//        .setInitialLoadSizeHint(20)
        .setEnablePlaceholders(false)
        .setPageSize(40)
        .build()
}