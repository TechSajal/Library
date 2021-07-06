package com.example.library

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BookRecyclerView( private val mContext:Context, private val book:ArrayList<Book>): RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.recyclerviewbooks,parent,false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
    val  data = book[position]
        holder.bookname.text = data.bookname
        holder.bookwritter.text = data.booktitle
        Glide.with(mContext).load(data.bookimage).into(holder.bookimage)

    }

    override fun getItemCount(): Int {
       return book.size
    }
}


class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val bookname:TextView = itemView.findViewById(R.id.bookname_recycler)
    val bookwritter:TextView = itemView.findViewById(R.id.bookauthor)
    val bookimage:ImageView = itemView.findViewById(R.id.bookimage_recycler)
    val relativelayout :RelativeLayout = itemView.findViewById(R.id.relativelayout)

}