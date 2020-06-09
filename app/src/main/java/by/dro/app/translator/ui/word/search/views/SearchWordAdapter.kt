package by.dro.app.translator.ui.word.search.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.dro.app.translator.R
import by.dro.app.translator.model.Word
import by.dro.app.translator.api.NetworkState


class SearchWordAdapter(private val callback: OnClickListener): PagedListAdapter<Word, RecyclerView.ViewHolder>(
    diffCallback
) {


    private val pool = RecyclerView.RecycledViewPool()
    private var networkState: NetworkState? = null
    interface OnClickListener {
        fun onClickToMeaning(id: Int?)
        fun onClickRetry()
        fun whenListIsUpdated(size: Int, networkState: NetworkState?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_search_word -> SearchWordViewHolder(view, callback, pool)
            R.layout.item_search_word_network_state -> SearchWordNetworkStateViewHolder(view, callback)
            else -> throw IllegalArgumentException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_search_word -> (holder as SearchWordViewHolder).bindTo(getItem(position))
            R.layout.item_search_word_network_state -> (holder as SearchWordNetworkStateViewHolder).bindTo(networkState)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_search_word_network_state
        } else {
            R.layout.item_search_word
        }
    }

    override fun getItemCount(): Int {
        this.callback.whenListIsUpdated(super.getItemCount(), this.networkState)
        return super.getItemCount()
    }


    private fun hasExtraRow() = networkState != null && networkState != NetworkState.SUCCESS

    fun updateNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem == newItem
        }
    }
}