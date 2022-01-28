package com.heckbot.makeminemarvel.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.heckbot.makeminemarvel.Dependencies
import com.heckbot.makeminemarvel.R
import com.heckbot.model.Comic

class ComicAdapter(val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    private val comicList = mutableListOf<Comic>()

    class ComicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivCover: ImageView = itemView.findViewById(R.id.ivCover)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDetails: TextView = itemView.findViewById(R.id.tvDetails)
    }

    fun updateList(newList: List<Comic>) {
        comicList.clear()
        comicList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_comic, parent, false)

        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comicList[position]
        Dependencies.imageLoader.loadBitmapFromUrlIntoImageView(comic.coverUrl ?: "", holder.ivCover)
        holder.tvTitle.text = comic.title
        holder.tvDetails.text = comic.details
        holder.itemView.setOnClickListener { onItemClick(position) }
    }

    override fun getItemCount() = comicList.size
}