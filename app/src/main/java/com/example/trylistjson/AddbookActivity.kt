package com.example.trylistjson

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.trylistjson.models.Book
import com.example.trylistjson.models.BookType
import java.util.concurrent.Executors

class AddbookActivity : AppCompatActivity() {
    //лист который можно имзменять

    private lateinit var title: EditText
    private lateinit var genre: EditText
    private lateinit var author: EditText
    private lateinit var year_of_publishing: EditText
    private lateinit var cover_type: EditText
    private lateinit var NUMBER_OF_PAGES: EditText
    private lateinit var button: Button
    private var BookList: MutableList<Book> = mutableListOf()
    var year_of_publishing_numConvert: Int = -1
    var NUMBER_OF_PAGES_numConvert: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        // метод
        this.supportActionBar!!.hide()
        val index = intent.getIntExtra("number", -1)
        title = findViewById(R.id.editTextTexttitle)
        genre = findViewById(R.id.editTextTexjanr)
        author = findViewById(R.id.editTextTextEmailAuhtor)
        year_of_publishing = findViewById(R.id.editTextTextyear_of_publishing)
        cover_type = findViewById(R.id.editTextTextcover_type)
        NUMBER_OF_PAGES = findViewById(R.id.editTextTextNUMBER_OF_PAGES)
        button = findViewById(R.id.button3)
        Toast.makeText(this, "test: " + index.toString(), Toast.LENGTH_SHORT).show()
        AddInfo_in_List()
        if(index>-1){
            button.text="изменить"
           /* title.setText(BookList[index].title_data)
            genre.setText(BookList[index].genre_data_from_book_class)
            author.setText(BookList[index].author_data)
            year_of_publishing.setText(BookList[index].year_of_publishing_data)
            cover_type.setText(BookList[index].cover_type_data)
            NUMBER_OF_PAGES.setText(BookList[index].NUMBER_OF_PAGES_data)*/

        }


        button.setOnClickListener {
            if (index == -1) {

                var db: Bookdatabase = Room.databaseBuilder(
                    this, Bookdatabase::class.java, DATABASE_NAME
                ).build()
                val bookDAO = db.bookDao()
                val executor = Executors.newSingleThreadExecutor()
                val executor2 = Executors.newSingleThreadExecutor()

                //принятие данных эдит текста 1
                val num1: String = year_of_publishing.text.toString()
                //принятие данных эдит текста 2
                val num2: String = NUMBER_OF_PAGES.text.toString()


                try {
                    year_of_publishing_numConvert = num1!!.toInt()
                    NUMBER_OF_PAGES_numConvert = num2!!.toInt()


                } catch (e: Exception) {
                    Toast.makeText(this, "repeat the input ", Toast.LENGTH_LONG).show();

                }
                executor.execute {
                    bookDAO.addGenre(BookType(0, genre.text.toString()))

                }/*
            var get_genre_String_from_Bookclass:String="  13313122"
            bookDAO.getOnlyGenre(genre.text.toString()).observe(this,androidx.lifecycle.Observer {

                   get_genre_String_from_Bookclass=it.genre_data
                   Log.d("TAG1432","ID: ${it.genre_data}")
                   Log.d("TAG1432","ID: ${get_genre_String_from_Bookclass}")


               })*/
                executor2.execute {
                    bookDAO.addBookMain(
                        Book(
                            0,
                            title.text.toString(),
                            author.text.toString(),
                            year_of_publishing_numConvert,
                            cover_type.text.toString(),
                            NUMBER_OF_PAGES_numConvert,
                            genre.text.toString()
                        )
                    )

                }


                val types = bookDAO.getAllGenre()
                val book34 = bookDAO.getAllBookMain()
                types.observe(this, androidx.lifecycle.Observer {
                    it.forEach {
                        Log.d("TAG1", "ID: ${it.id} ЖАНР: ${it.genre_data}")
                    }

                })


                book34.observe(this, androidx.lifecycle.Observer {
                    it.forEach {
                        Log.d(
                            "TAG12",
                            "ID:${it.id}   НАЗВАНИЕ:${it.title_data}  Автор:${it.author_data}  ГОД ПУБЛИКАЦИИ:${it.year_of_publishing_data} ТИП ОБЛОЖКИ: ${it.cover_type_data} КОЛИЧЕСТВО СТРАНИЦ:${it.NUMBER_OF_PAGES_data} ЖАНР: ${it.genre_data_from_book_class}"
                        )
                    }

                })

            }
            if(index>-1){
                val executor = Executors.newSingleThreadExecutor()
                var db: Bookdatabase = Room.databaseBuilder(
                    this, Bookdatabase::class.java, DATABASE_NAME
                ).build()
                val bookDAO = db.bookDao()
                executor.execute {
                    bookDAO.savetBookMain(
                        Book(
                            index,
                            title.text.toString(),
                            author.text.toString(),
                            year_of_publishing_numConvert,
                            cover_type.text.toString(),
                            NUMBER_OF_PAGES_numConvert,
                            genre.text.toString()
                        )
                    )

                }


                Toast.makeText(this, "значения изменены", Toast.LENGTH_SHORT).show()

            }



        }

    }

    private fun AddInfo_in_List(){
        BookList.clear()
        var db:Bookdatabase= Room.databaseBuilder(this, Bookdatabase::class.java
            , DATABASE_NAME ).build()
        val moneyDAO=db.bookDao()
        var booksget = moneyDAO.getAllBookMain()
        booksget.observe(this, androidx.lifecycle.Observer {
            BookList.addAll(it)
        })
    }



}


