package com.example.quotesappusingroomandretrofitmvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quotesappusingroomandretrofitmvvm.repository.Response
import com.example.quotesappusingroomandretrofitmvvm.viewmodels.MainViewModel
import com.example.quotesappusingroomandretrofitmvvm.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val repository = (application as QuoteApplication).quoteRepository
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            when (it) {
                is Response.Loading -> {}
                is Response.Success -> {
                    it.data?.let {

                        Toast.makeText(this, it.results.size.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

                is Response.Error -> {
                    Toast.makeText(this, "Some Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
