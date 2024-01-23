package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.databinding.ActivityTimerLiveDataBinding

class TimerLiveData : AppCompatActivity() {

    private lateinit var liveDataTimerViewModel: TimerLiveDataModel
    private lateinit var timerLiveDataBinding: ActivityTimerLiveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timerLiveDataBinding = ActivityTimerLiveDataBinding.inflate(layoutInflater)
        setContentView(timerLiveDataBinding.root)

        // Perbaikan: Menggunakan ViewModelProvider.AndroidViewModelFactory
        liveDataTimerViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[TimerLiveDataModel::class.java]

        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long?> { aLong ->
            val newText = this@TimerLiveData.resources.getString(R.string.seconds, aLong)
            timerLiveDataBinding.timerTextview.text = newText
        }
        // Perbaikan: Menggunakan fungsi getElapsedTime() yang benar
        liveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}
