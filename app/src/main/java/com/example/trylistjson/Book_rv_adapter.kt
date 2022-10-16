package com.example.trylistjson


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.trylistjson.models.Book
import com.example.trylistjson.models.BookType
import com.google.gson.TypeAdapter
import kotlin.math.log


class Book_rv_adapter (сontext:Context? , val data:MutableList<Book> ,val data1:MutableList<BookType>): RecyclerView.Adapter<Book_rv_adapter.Book_view_Holder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(сontext)

    private var iClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Book_view_Holder {
        val view :View = layoutInflater.inflate(R.layout.item_book,parent,false)
        return  Book_view_Holder(view)
    }

    override fun onBindViewHolder(holder: Book_view_Holder, position: Int) {
        val  item= data[position]
        val  item2= data1[position]
        holder.genreTextView.text =       "Жанр : "+item2.genre_data
        holder.nameTextView.text =       "Название : "+item.title_data
        holder.AuhtorTextView.text=      "Автор : "+item.author_data
        holder.publishingTextView.text = "Год публикации: "+item.year_of_publishing_data
        holder.cover_typeTextView.text = "Тип обиложки: "+item.cover_type_data
        holder.PAGESTextView.text=       "количество страниц: "+item.NUMBER_OF_PAGES_data
    }
    override fun getItemCount(): Int =data.size

    // холдер
    inner class Book_view_Holder (iteamView: View): RecyclerView.ViewHolder(iteamView),View.OnClickListener {
        var genreTextView: TextView =iteamView.findViewById(R.id.tv_janr)
        var nameTextView: TextView =iteamView.findViewById(R.id.tv_title)
        var AuhtorTextView: TextView =iteamView.findViewById(R.id.tv_Auhtor)
        var publishingTextView: TextView =iteamView.findViewById(R.id.tv_year_of_publishing)
        var cover_typeTextView: TextView =iteamView.findViewById(R.id.tv_cover_type)
        var PAGESTextView: TextView =iteamView.findViewById(R.id.tv_NUMBER_OF_PAGES)
        init {
            iteamView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            iClickListener?.onItemClick(view , adapterPosition)
        }

    }
    fun setClickListener(itemClickListener: ItemClickListener?){
        iClickListener = itemClickListener
    }

    interface ItemClickListener{
        fun onItemClick(view: View?, position: Int)
    }







}