package com.example.trylistjson.models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trylistjson.MAIN_BOOK_TABLE

@Entity(tableName = MAIN_BOOK_TABLE)
data class Book(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val id: Int,
    @ColumnInfo(index = true)
    var title_data: String=" ",
    var author_data: String=" ",
    val year_of_publishing_data :Int=0,
    var cover_type_data:String=" ",
    val NUMBER_OF_PAGES_data :Int=0,


    )