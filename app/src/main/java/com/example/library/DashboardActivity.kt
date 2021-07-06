package com.example.library

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
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

           // history genge sorting
        val History :AppCompatButton = findViewById(R.id.history)
        val romantic:AppCompatButton = findViewById(R.id.romantic)
        val funny:AppCompatButton=findViewById(R.id.funny)
        val scifi:AppCompatButton = findViewById(R.id.scifi)
        val politics:AppCompatButton = findViewById(R.id.politics)
        val food:AppCompatButton = findViewById(R.id.food)
        val selflove:AppCompatButton = findViewById(R.id.selflove)
        val myestry:AppCompatButton = findViewById(R.id.mystery)
        History.setOnClickListener {
            val filterlist = ArrayList<Book>()
                 for (item in productlists){
                     if (item.bookgenre.toLowerCase(Locale.getDefault()).contains("history")){
                         filterlist.add(item)
                     }
                 }
                 val adapthistory = BookRecyclerView(this@DashboardActivity,filterlist)
                 recyclerView.adapter = adapthistory
        }

        romantic.setOnClickListener {
            val filterlist = ArrayList<Book>()
            for (item in productlists){
                if (item.bookgenre.toLowerCase(Locale.getDefault()).contains("romantic")){
                    filterlist.add(item)
                }
            }
            val adapterromantic = BookRecyclerView(this@DashboardActivity,filterlist)
            recyclerView.adapter = adapterromantic
        }
        funny.setOnClickListener {
            val filterlist = ArrayList<Book>()
            for (item in productlists){
                if (item.bookgenre.toLowerCase(Locale.getDefault()).contains("Funny")){
                    filterlist.add(item)
                }
            }
            val adapterfunny = BookRecyclerView(this@DashboardActivity,filterlist)
            recyclerView.adapter = adapterfunny
        }

        scifi.setOnClickListener {
            val filterlist = ArrayList<Book>()
            for (item in productlists){
                if (item.bookgenre.toLowerCase(Locale.getDefault()).contains("sci-fi")){
                    filterlist.add(item)
                }
            }
            val adapthistory = BookRecyclerView(this@DashboardActivity,filterlist)
            recyclerView.adapter = adapthistory
        }

        politics.setOnClickListener {
            val filterlist = ArrayList<Book>()
            for (item in productlists){
                if (item.bookgenre.toLowerCase(Locale.getDefault()).contains("politics")){
                    filterlist.add(item)
                }
            }
            val adapthistory = BookRecyclerView(this@DashboardActivity,filterlist)
            recyclerView.adapter = adapthistory
        }
        food.setOnClickListener {
            val filterlist = ArrayList<Book>()
            for (item in productlists){
                if (item.bookgenre.toLowerCase(Locale.getDefault()).contains("food")){
                    filterlist.add(item)
                }
            }
            val adapthistory = BookRecyclerView(this@DashboardActivity,filterlist)
            recyclerView.adapter = adapthistory
        }
        selflove.setOnClickListener {
            val filterlist = ArrayList<Book>()
            for (item in productlists){
                if (item.bookgenre.toLowerCase(Locale.getDefault()).contains("selflove")){
                    filterlist.add(item)
                }
            }
            val adapthistory = BookRecyclerView(this@DashboardActivity,filterlist)
            recyclerView.adapter = adapthistory
        }

        myestry.setOnClickListener {
            val filterlist = ArrayList<Book>()
            for (item in productlists){
                if (item.bookgenre.toLowerCase(Locale.getDefault()).contains("mystery")){
                    filterlist.add(item)
                }
            }
            val adapthistory = BookRecyclerView(this@DashboardActivity,filterlist)
            recyclerView.adapter = adapthistory
        }


    }



    override fun onResume() {
        super.onResume()
        FirestoreClass().getdashboarditemlist(this)
    }
}
