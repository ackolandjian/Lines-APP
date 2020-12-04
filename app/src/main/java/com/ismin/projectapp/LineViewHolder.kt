package com.ismin.projectapp

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var txvNameLine = itemView.findViewById<TextView>(R.id.r_line_txv_name_line)
    var txvShortNameGroupOfLine: TextView = itemView.findViewById(R.id.r_line_txv_shortname_groupoflines)
    var txvTransport: TextView = itemView.findViewById(R.id.r_line_txv_transport)
    var unfavButton: ImageButton = itemView.findViewById(R.id.f_unfavorite_button)
    var favButton: ImageButton = itemView.findViewById(R.id.f_favorite_button)
}
