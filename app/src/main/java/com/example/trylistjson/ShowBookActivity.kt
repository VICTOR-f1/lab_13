package com.example.trylistjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.trylistjson.models.Book
import com.example.trylistjson.models.BookType

class ShowBookActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private var BookList: MutableList<Book> = mutableListOf()
    private var GenreList: MutableList<BookType> = mutableListOf()
    var index = -1
    private lateinit var adapterView: Book_rv_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_book)
        this.supportActionBar!!.hide()
        updeteInfo()

    }

    override fun onResume() {
        super.onResume()
        if(index>-1){
            updeteInfo()
        }

    }

    // метод
   private fun updeteInfo() {
        BookList.clear()
        GenreList.clear()
        var db:Bookdatabase= Room.databaseBuilder(this, Bookdatabase::class.java
            , DATABASE_NAME ).build()
        val moneyDAO=db.bookDao()
        var booksget = moneyDAO.getAllBookMain()
        var genreget = moneyDAO.getAllGenre()
        booksget.observe(this, androidx.lifecycle.Observer {
            BookList.addAll(it)
            Log.d("QWE", "its ok!")

        })
        genreget.observe(this,androidx.lifecycle.Observer {
            GenreList.addAll(it)
            FinalUpdeteInfo()
        })
    }
    private  fun FinalUpdeteInfo(){
        rv = findViewById(R.id.recycV)
        val adapter = Book_rv_adapter(this, BookList,GenreList)
        val rvListener = object : Book_rv_adapter.ItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                index = position
                val intent = Intent(this@ShowBookActivity, AddbookActivity::class.java)
                intent.putExtra("number", position)
                startActivity(intent)
            }
        }
        adapter.setClickListener(rvListener)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }
}





