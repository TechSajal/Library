package com.example.library

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class DashboardActivity : AppCompatActivity() {
//    private var recyclerView:RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
     val add :ImageView =findViewById(R.id.add)
        add.setOnClickListener {
            val i = Intent(this,BooksAddActivity::class.java)
            startActivity(i)
             finish()
        }
    }

    fun successDashboardItemList(productlists: ArrayList<Book>) {
         val recyclerView:RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)
        val adapter = BookRecyclerView(this,productlists)
        recyclerView.adapter = adapter
        val search :EditText = findViewById(R.id.activity_state_wise_search_editText)
          search.addTextChangedListener(object :TextWatcher{
              override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

              override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
              override fun afterTextChanged(s: Editable?) {
                  Filters(s.toString())
              }

              private fun Filters(text: String) {
                  val filterlist = ArrayList<Book>()
                  for (item in productlists){
                      if (item.bookname.toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))||item.booktitle.toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault())) ){
                           filterlist.add(item)
                      }
                  }
                  val adapt = BookRecyclerView(this@DashboardActivity,filterlist)
                   recyclerView.adapter = adapt
              }

          })



    }



    override fun onResume() {
        super.onResume()
        FirestoreClass().getdashboarditemlist(this)
    }
}
