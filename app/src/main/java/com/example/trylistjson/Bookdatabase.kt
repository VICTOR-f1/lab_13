package com.example.trylistjson

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trylistjson.converertes.DatesTypeConverter
import com.example.trylistjson.models.Book
import com.example.trylistjson.models.BookType

@Database(entities = [BookType::class,Book::class], version = 1)
@TypeConverters(DatesTypeConverter::class)
abstract  class Bookdatabase:RoomDatabase() {

    abstract  fun bookDao():BookDAO
}