package by.dro.app.translator.ui.meaning

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.dro.app.translator.R
import by.dro.app.translator.extensions.loadUrl
import by.dro.app.translator.model.Meaning
import by.dro.app.translator.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_meaning.*
import org.koin.android.viewmodel.ext.android.viewModel

class MeaningFragment : Fragment(R.layout.fragment_meaning) {

    companion object {
        private const val ARG_IDS = "ARG_IDS"

        @JvmStatic
        fun newInstance(param1: String) =
            MeaningFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IDS, param1)
                }
            }
    }

    private val viewModel: MeaningViewModel by viewModel()
    private var mediaPlayer: MediaPlayer? = null
    private var meaningItem: Meaning? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            it.getString(ARG_IDS)?.let {id ->

                Log.d("kkk", viewModel.toString())
                viewModel.getMeanings(id)
            }

        }
        observe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_meaning_play_sound.setOnClickListener {
             playAudio()
        }
    }

    private fun bind(meaning: Meaning){
        meaningItem = meaning

        meaning.soundUrl?.also { prepareAudio(it) }
        fragment_meaning_transcription.text = meaning.transcription
        fragment_meaning_text.text = meaning.text
        fragment_meaning_translation.text = meaning.translation?.text
        fragment_meaning_update_date.text = meaning.updatedAt
        if (!meaning.images.isNullOrEmpty())  fragment_meaning_img.loadUrl(meaning.images[0].url)

        fragment_meaning_transcription.visibility = View.VISIBLE
        fragment_meaning_text.visibility = View.VISIBLE
        fragment_meaning_translation.visibility = View.VISIBLE
        fragment_meaning_update_date.visibility = View.VISIBLE
        fragment_meaning_img.visibility = View.VISIBLE
        fragment_meaning_play_sound.visibility = View.VISIBLE
        fragment_meaning_progress.visibility = View.GONE
    }

    private fun observe(){
        viewModel.getMeaningsLiveData().observe(this, Observer {
            if (!it.isNullOrEmpty()) bind(it[0])
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
        = when(item.itemId){
            android.R.id.home ->{
                activity?.supportFragmentManager?.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    private fun playAudio() {
        mediaPlayer?.start()
        fragment_meaning_play_sound.setIconResource(R.drawable.ic_pause_24px)
        fragment_meaning_play_sound.isEnabled = false
    }


    private fun prepareAudio(url: String){
        killMediaPlayer()
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setOnCompletionListener {
            Log.d("kkk", "r?.setOnCompletionLi")
            fragment_meaning_play_sound.setIconResource(R.drawable.ic_play_arrow_24px)
            fragment_meaning_play_sound.isEnabled = true
        }
        mediaPlayer?.setDataSource("https:$url")
        mediaPlayer?.prepare()
        fragment_meaning_play_sound.isEnabled = true
    }

    private fun killMediaPlayer() {
        try {
            mediaPlayer?.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}