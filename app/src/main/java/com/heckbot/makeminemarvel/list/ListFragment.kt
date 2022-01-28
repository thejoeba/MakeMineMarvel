package com.heckbot.makeminemarvel.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heckbot.makeminemarvel.ComicsViewModel
import com.heckbot.makeminemarvel.Dependencies
import com.heckbot.makeminemarvel.R

class ListFragment : Fragment(R.layout.fragment_list) {
    val comicsViewModel by activityViewModels<ComicsViewModel>()
    val adapter = ComicAdapter {
        Dependencies.logger.d(TAG, "Item Clicked $it")
        comicsViewModel.comicSelected(it)
    }
    lateinit var rvComics: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvComics = view.findViewById<RecyclerView>(R.id.rvComics)
        rvComics.layoutManager = LinearLayoutManager(view.context)
        rvComics.adapter = adapter

        comicsViewModel.comicsLiveData.observe(viewLifecycleOwner, {
            adapter.updateList(it)
        })
    }

    override fun onPause() {
        super.onPause()
        comicsViewModel.recyclerViewState = rvComics.layoutManager?.onSaveInstanceState()
    }

    override fun onResume() {
        super.onResume()
        rvComics.layoutManager?.onRestoreInstanceState(comicsViewModel.recyclerViewState)
    }

    companion object {
        const val TAG = "ListFragment"
    }
}