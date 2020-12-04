package com.ismin.projectapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LineAdapter(
        private var lines:ArrayList<Line>,
        private val fragmentInteractionListener: LineListFragment.OnFragmentInteractionListener?,
        private val context: Context?,
        private val favoriteListener:onFavoriteListener): RecyclerView.Adapter<LineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_line, parent, false)
        return LineViewHolder(row)
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        val line = this.lines[position]
        holder.txvNameLine.text = line.name_line
        holder.txvShortNameGroupOfLine.text = line.shortname_groupoflines
        holder.txvTransport.text = line.transportmode

        holder.unfavButton.setOnClickListener{
            favoriteListener.unfavFavorite(position)
            holder.unfavButton.visibility = View.GONE
            holder.favButton.visibility = View.VISIBLE
        }

        holder.favButton.setOnClickListener{
            favoriteListener.unfavFavorite(position)
            holder.unfavButton.visibility = View.VISIBLE
            holder.favButton.visibility = View.GONE
        }

        if (context != null) {
            holder.itemView.setOnClickListener {
                fragmentInteractionListener?.onItemClicked(line)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.lines.size
    }

    fun updateLine(linesToDisplay: List<Line>){
        lines.clear();
        lines.addAll(linesToDisplay)
        notifyDataSetChanged();
    }

    fun updateList(lineList: ArrayList<Line>){
        lines = lineList
        notifyDataSetChanged()
    }
}
