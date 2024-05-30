package com.example.portscanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.portscanner.adapters.DomainAdapter
import com.example.portscanner.databinding.ActivityMainBinding
import com.example.portscanner.model.Address
import com.example.portscanner.retrofit.Common
import com.scanner.scanner.model.DomainCVE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), DomainAdapter.OnClickListener {
    lateinit var adapter: DomainAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener{
            adapter = DomainAdapter(this, mutableListOf(DomainCVE(binding.etSearch.text.toString())), true)
            binding.rvDomains.layoutManager = LinearLayoutManager(this)
            binding.rvDomains.adapter = adapter
            adapter.notifyDataSetChanged()
            getData(binding.etSearch.text.toString())
        }
    }

    fun getData(address: String) {
        Common.domainService.getDomainCVE(Address(address)).enqueue(object : Callback<DomainCVE> {

            override fun onFailure(p0: Call<DomainCVE>, p1: Throwable) {
                Toast.makeText(applicationContext, "WRONG DOMAIN PLS BACK", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(p0: Call<DomainCVE>, response: Response<DomainCVE>) {
                adapter = DomainAdapter(this@MainActivity, mutableListOf(response.body()!!), false)
                binding.rvDomains.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onClick(domain: DomainCVE) {
        val intent = Intent(this, DomainActivity::class.java)
        intent.putExtra("domain", domain)
        Toast.makeText(this, "PPP", Toast.LENGTH_LONG)
        startActivity(intent)
    }
}
