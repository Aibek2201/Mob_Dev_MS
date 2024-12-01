package com.example.quotesappusingroomandretrofitmvvm.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.quotesappusingroomandretrofitmvvm.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class QuoteWorker(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {

        Log.d("XOKSIS","Worker Called")
        val repository = (context as QuoteApplication).quoteRepository

        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotesBackground()
        }
        return Result.success()
    }
}