package com.example.trylistjson

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trylistjson.models.Book
import com.example.trylistjson.models.BookType

@Dao
interface BookDAO {
/* таблица BookType*/
    @Query("SELECT * FROM $GENRE_TABLE ")
    fun getAllGenre():LiveData<List<BookType>>
    @Insert
    fun addGenre(bookType: BookType)
    @Update
    fun  savetGenre(bookType: BookType)
    @Delete
    fun killGenre(bookType: BookType)
    /* таблица Book*/
    @Query("SELECT * FROM $MAIN_BOOK_TABLE")
    fun getAllBookMain():LiveData<List<Book>>
    @Query("SELECT * FROM $MAIN_BOOK_TABLE WHERE _id=:id")
    fun  getBookMain(id:Int):LiveData<Book>
    @Insert
    fun addBookMain(book: Book)
    @Update
    fun  savetBookMain(book: Book)
    @Delete
    fun  killBookMain(book: Book)
}