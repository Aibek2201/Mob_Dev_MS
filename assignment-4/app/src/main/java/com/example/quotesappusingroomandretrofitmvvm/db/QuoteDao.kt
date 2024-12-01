package com.example.quotesappusingroomandretrofitmvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quotesappusingroomandretrofitmvvm.models.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<ResultEntity>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<ResultEntity>

}