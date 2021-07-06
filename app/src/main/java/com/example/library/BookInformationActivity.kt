package com.example.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class BookInformationActivity : AppCompatActivity() {
    var bookname:String? = null
    var bookauthor:String? = null
    var bookdricption:String? = null
    var bookghene:String? = null
    var  bookimage:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_information)
       val intent:Intent = intent
        bookname = intent.getStringExtra("bookname")
        bookauthor = intent.getStringExtra("bookauthor")
        bookdricption = intent.getStringExtra("bookdricption")
        bookghene = intent.getStringExtra("bookghene")
        bookimage = intent.getStringExtra("bookimage")
        val bi :ImageView = findViewById(R.id.imageView3)
        val bname:TextView = findViewById(R.id.booknamerecy)
        val bauthor:TextView = findViewById(R.id.author_name)
        val bdiscription:TextView = findViewById(R.id.book_discription_)
        val bghene:TextView = findViewById(R.id.bookgenre_)
        Glide.with(this).load(bookimage).into(bi)
        bname.text = bookname
        bauthor.text =bookauthor
        bdiscription.text = bookdricption
        bghene.text = bookghene

    }
}