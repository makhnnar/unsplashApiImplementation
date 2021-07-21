package com.pedrogomez.develepersfinder.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pedrogomez.develepersfinder.databinding.ActivityMainBinding
import com.pedrogomez.develepersfinder.view.viewmodel.SharedHitsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sharedHitsViewModel : SharedHitsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}