package com.fm.lastfm

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DataAdpter(
    private
    var dataList: List<AlbumItem>, private val context: Context,              private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<DataAdpter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.listcard, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataList.get(position)
        val data = dataList.get(position)
        holder.titleTextView.text = dataModel.name
        holder.title.text = dataModel.artist

        Glide.with(context)
            .load(dataModel.image?.get(2)?.text.toString())
            .into(holder.image);


        holder.titleTextView.setOnClickListener {
            cellClickListener.onCellClickListener(dataModel.image?.get(2)?.text.toString(),data.name.toString(),data.artist.toString(),data.url.toString())
        }

    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        lateinit
        var titleTextView: TextView
        lateinit var image : ImageView

        lateinit
        var title: TextView

        init {
            titleTextView = itemLayoutView.findViewById(R.id.name)
            title = itemLayoutView.findViewById(R.id.url)
            image = itemLayoutView.findViewById(R.id.image)
        }

    }

    fun filterList(filteredNames: ArrayList<AlbumItem>) {
        Log.e("list", filteredNames.toString())
        Log.e("list", filteredNames.size.toString())
        // this.dataList.clear()
        this.dataList = filteredNames
        notifyDataSetChanged()
    }
}