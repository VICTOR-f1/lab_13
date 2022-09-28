package com.example.trylistjson

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.trylistjson.models.Book
import com.example.trylistjson.models.BookType
import java.util.concurrent.Executors

class AddbookActivity : AppCompatActivity() {
   //лист который можно имзменять

    private lateinit var title:EditText
    private lateinit var genre:EditText
    private lateinit var author:EditText
    private lateinit var year_of_publishing:EditText
    private lateinit var cover_type:EditText
    private lateinit var NUMBER_OF_PAGES:EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        // метод
            this.supportActionBar!!.hide()
        val index=intent.getIntExtra("number",-1)
        title = findViewById(R.id.editTextTexttitle)
        genre = findViewById(R.id.editTextTexjanr)
        author = findViewById(R.id.editTextTextEmailAuhtor)
        year_of_publishing = findViewById(R.id.editTextTextyear_of_publishing)
        cover_type = findViewById(R.id.editTextTextcover_type)
        NUMBER_OF_PAGES = findViewById(R.id.editTextTextNUMBER_OF_PAGES)
        button = findViewById(R.id.button3)
        Toast.makeText(this, "test: "+index.toString(), Toast.LENGTH_SHORT).show()
/*
        if(index>-1){
            button.text="изменить"
            title.setText(BookList[index].title)
            genre.setText(BookList[index].genre)
            author.setText(BookList[index].author)
            year_of_publishing.setText(BookList[index].year_of_publishing)
            cover_type.setText(BookList[index].cover_type)
            NUMBER_OF_PAGES.setText(BookList[index].number_of_pages)

        }
*/

        button.setOnClickListener {
            var db:Bookdatabase= Room.databaseBuilder(this, Bookdatabase::class.java
                , DATABASE_NAME ).build()
            val moneyDAO=db.bookDao()
            val executor= Executors.newSingleThreadExecutor()
            //принятие данных эдит текста 1
            val num1: String = year_of_publishing.text.toString()
            //принятие данных эдит текста 2
            val num2: String = NUMBER_OF_PAGES.text.toString()
            var year_of_publishing_numConvert:Int= -1
            var NUMBER_OF_PAGES_numConvert:Int= -1

            try {
                year_of_publishing_numConvert= num1!!.toInt()
                NUMBER_OF_PAGES_numConvert= num2!!.toInt()


            }
            catch(e: Exception){
                Toast.makeText(this,"repeat the input ", Toast.LENGTH_LONG).show();

            }

            executor.execute{
                moneyDAO.addGenre(BookType(0,genre.text.toString()))
                 moneyDAO.addBookMain(Book(0,title.text.toString(),author.text.toString(),year_of_publishing_numConvert,cover_type.text.toString(),NUMBER_OF_PAGES_numConvert  ))

            }
            val types =moneyDAO.getAllGenre()
            val book34 = moneyDAO.getAllBookMain()
            types.observe(this,androidx.lifecycle.Observer {
                 it.forEach{
                     Log.d("TAG1","ID: ${it.id} ЖАНР: ${it.genre_data}")
                 }

            })


            book34.observe(this,androidx.lifecycle.Observer {
                it.forEach{
                   Log.d("TAG12","ID:${it.id}   НАЗВАНИЕ:${it.title_data}  Автор:${it.author_data}  ГОД ПУБЛИКАЦИИ:${it.year_of_publishing_data} ТИП ОБЛОЖКИ: ${it.cover_type_data} КОЛИЧЕСТВО СТРАНИЦ:${it.NUMBER_OF_PAGES_data}")      }

            })

            }

        }
    }

