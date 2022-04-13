package com.example.networkingwithretrofitchapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.networkingwithretrofitchapter5.databinding.ActivityMainBinding
import com.example.networkingwithretrofitchapter5.network.CarsApi
import com.example.networkingwithretrofitchapter5.request.RegisterRequest
import com.example.networkingwithretrofitchapter5.model.RegisterResponseItem


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}
