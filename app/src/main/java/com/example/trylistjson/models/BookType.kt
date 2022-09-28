package com.example.trylistjson.models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trylistjson.GENRE_TABLE

@Entity(tableName = GENRE_TABLE)

data class BookType (
@PrimaryKey(autoGenerate = true)
@ColumnInfo(name=BaseColumns._ID)
val id:Int ,
var genre_data:String

)