package com.example.quotesappusingroomandretrofitmvvm.models

import com.example.quotesappusingroomandretrofitmvvm.db.ResultEntity

data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<ResultEntity>,
    val totalCount: Int,
    val totalPages: Int
)