package com.example.portscanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.portscanner.databinding.ActivityDomainBinding
import com.example.portscanner.databinding.ActivityMainBinding
import com.example.portscanner.retrofit.Common

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener{
            val intent = Intent(this, DomainActivity::class.java)
            intent.putExtra("address", binding.etSearch.text.toString())
            startActivity(intent)
        }
    }
}