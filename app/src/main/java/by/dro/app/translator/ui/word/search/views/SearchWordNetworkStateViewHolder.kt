package by.dro.app.translator.ui.word.search.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.dro.app.translator.api.NetworkState
import kotlinx.android.synthetic.main.item_search_word_network_state.view.item_search_user_network_state_progress_bar as progressBar
import kotlinx.android.synthetic.main.item_search_word_network_state.view.item_search_user_network_state_button as retryButton
import kotlinx.android.synthetic.main.item_search_word_network_state.view.item_search_user_network_state_title as retryTitle

class SearchWordNetworkStateViewHolder(parent: View, callback: SearchWordAdapter.OnClickListener): RecyclerView.ViewHolder(parent) {

    init {
        itemView.retryButton.setOnClickListener { callback.onClickRetry() }
    }

    fun bindTo(networkState: NetworkState?){
        hideViews()
        setVisibleRightViews(networkState)
    }


    private fun hideViews() {
        itemView.retryButton.visibility = View.GONE
        itemView.retryTitle.visibility = View.GONE
        itemView.progressBar.visibility = View.GONE
    }

    private fun setVisibleRightViews(networkState: NetworkState?) {
        when (networkState) {
            NetworkState.FAILED -> {
                itemView.retryButton.visibility = View.VISIBLE
                itemView.retryTitle.visibility = View.VISIBLE
            }
            NetworkState.RUNNING -> {
                itemView.progressBar.visibility = View.VISIBLE
            }
        }
    }
}