package by.dro.app.translator.ui.word.search


import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import by.dro.app.translator.R
import com.bumptech.glide.Glide

import by.dro.app.translator.api.NetworkState
import by.dro.app.translator.extensions.onQueryTextChange
import by.dro.app.translator.ui.MainActivity
import by.dro.app.translator.ui.ShowFragment
import by.dro.app.translator.ui.meaning.MeaningFragment
import by.dro.app.translator.ui.word.search.views.SearchWordAdapter
import kotlinx.android.synthetic.main.fragment_search_word.fragment_search_word_rv as recyclerView
import kotlinx.android.synthetic.main.fragment_search_word.fragment_search_word_empty_list_image as emptyListImage
import kotlinx.android.synthetic.main.fragment_search_word.fragment_search_word_empty_list_title as emptyListTitle
import kotlinx.android.synthetic.main.fragment_search_word.fragment_search_word_empty_list_button as emptyListButton
import kotlinx.android.synthetic.main.fragment_search_word.fragment_search_word_progress as progressBar
import org.koin.android.viewmodel.ext.android.viewModel


class SearchWordFragment : Fragment(R.layout.fragment_search_word), SearchWordAdapter.OnClickListener {

    private val viewModel: SearchWordViewModel by viewModel()
    private lateinit var adapter: SearchWordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        configureObservables()
        configureOnClick()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        configureMenu(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClickToMeaning(id: Int?) {
        (activity as ShowFragment).showFragment(MeaningFragment.newInstance(id.toString()))
    }

    override fun onClickRetry() {
        viewModel.refreshFailedRequest()
    }

    override fun whenListIsUpdated(size: Int, networkState: NetworkState?) {
        updateUIWhenLoading(size, networkState)
        updateUIWhenEmptyList(size, networkState)
    }

    private fun configureOnClick(){
        emptyListButton.setOnClickListener { viewModel.refreshAllList() }

    }


    private fun configureMenu(menu: Menu) {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val searchMenuItem = menu.findItem(R.id.action_search)
        val possibleExistingQuery = viewModel.getCurrentQuery()
        val searchView = searchMenuItem.actionView as SearchView
        if (possibleExistingQuery.isNotEmpty()) {
            searchMenuItem.expandActionView()
            searchView.setQuery(possibleExistingQuery, false)
            searchView.clearFocus()
        }
        searchView.onQueryTextChange {
            if (it.isNotEmpty()) viewModel.searchWords(it)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        adapter.submitList(viewModel.words.value)
    }

    private fun configureRecyclerView(){
        adapter = SearchWordAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun configureObservables() {
        viewModel.networkState?.observe(this, Observer { adapter.updateNetworkState(it) })
        viewModel.words.observe(this, Observer {
            Log.d("kkk", " adapter.submitList(it)")
            adapter.submitList(it)

        })
    }

    private fun updateUIWhenEmptyList(size: Int, networkState: NetworkState?) {
        emptyListImage.visibility = View.GONE
        emptyListButton.visibility = View.GONE
        emptyListTitle.visibility = View.GONE
        if (size == 0) {
            when (networkState) {
                NetworkState.SUCCESS -> {
                    Glide.with(this).load(R.drawable.ic_directions_run_black_24dp).into(emptyListImage)
                    emptyListTitle.text = getString(R.string.no_result_found)
                    emptyListImage.visibility = View.VISIBLE
                    emptyListTitle.visibility = View.VISIBLE
                    emptyListButton.visibility = View.GONE
                }
                NetworkState.FAILED -> {
                    Glide.with(this).load(R.drawable.ic_healing_black_24dp).into(emptyListImage)
                    emptyListTitle.text = getString(R.string.technical_error)
                    emptyListImage.visibility = View.VISIBLE
                    emptyListTitle.visibility = View.VISIBLE
                    emptyListButton.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun updateUIWhenLoading(size: Int, networkState: NetworkState?) {
        progressBar.visibility = if (size == 0 && networkState == NetworkState.RUNNING) View.VISIBLE else View.GONE
    }

}
