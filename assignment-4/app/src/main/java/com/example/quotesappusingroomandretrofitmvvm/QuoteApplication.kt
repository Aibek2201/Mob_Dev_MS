package com.example.quotesappusingroomandretrofitmvvm

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.quotesappusingroomandretrofitmvvm.api.QuoteService
import com.example.quotesappusingroomandretrofitmvvm.api.RetrofitHelper
import com.example.quotesappusingroomandretrofitmvvm.db.QuoteDatabase
import com.example.quotesappusingroomandretrofitmvvm.repository.QuoteRepository
import com.example.quotesappusingroomandretrofitmvvm.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun setupWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(QuoteWorker::class.java, 35, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)

    }
}