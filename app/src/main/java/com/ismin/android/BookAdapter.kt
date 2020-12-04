package com.ismin.android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class BookAdapter(private var books: ArrayList<Book>, private val fragmentInteractionListener: BookListFragment.OnFragmentInteractionListener?,
                  private val context: Context?, val favoriteListener: onFavoriteListener) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)
        return BookViewHolder(row)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
       // val (title, author, date) = this.books[position]

        val book = this.books[position]
        holder.txvTitle.text = book.name_line
        holder.txvAuthor.text = book.shortname_groupoflines
        holder.txvDate.text = book.transportmode

       // holder.txvNameLine.text = ligne.name_line
       // holder.txvShortNameGroupOfLine.text = ligne.shortname_groupoflines
       // holder.txvTransport.text = ligne.transportmode

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
                fragmentInteractionListener?.onItemClicked(book)
            }
        }

    }

    override fun getItemCount(): Int {
        return this.books.size
    }

   fun updateItem(booksToDisplay: List<Book>) {
        books.clear();
        books.addAll(booksToDisplay)
        notifyDataSetChanged();
    }

    fun updateList(bookList: ArrayList<Book>){
        books = bookList
        notifyDataSetChanged()
    }


}
