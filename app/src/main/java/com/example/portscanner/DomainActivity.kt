package com.example.portscanner

import android.app.Dialog
import android.app.ProgressDialog
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.DialogCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.portscanner.adapters.PortsAdapter
import com.example.portscanner.adapters.VulnersAdapter
import com.example.portscanner.databinding.ActivityDomainBinding
import com.example.portscanner.model.Address
import com.example.portscanner.retrofit.Common
import com.example.portscanner.retrofit.DomainService
import com.scanner.scanner.model.DomainCVE
import com.scanner.scanner.model.Ports
import com.scanner.scanner.model.Serv
import com.scanner.scanner.model.Vulners
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DomainActivity : AppCompatActivity(), PortsAdapter.OnClickListener {
    lateinit var portAdapter: PortsAdapter
    lateinit var vulnersAdapter: VulnersAdapter
    lateinit var binding: ActivityDomainBinding
    lateinit var domainCVE: DomainCVE


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        domainCVE = intent.getSerializableExtra("domain", DomainCVE::class.java)!!

        binding = ActivityDomainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val cveLayoutManager = LinearLayoutManager(this)
        binding.rvOpenPorts.layoutManager = layoutManager
        binding.rvCveList.layoutManager = cveLayoutManager

        binding.tvDomainName.text = domainCVE.address

        portAdapter = PortsAdapter(this, domainCVE.openPorts)
        binding.rvOpenPorts.adapter = portAdapter
        portAdapter.notifyDataSetChanged()

        vulnersAdapter = VulnersAdapter(mutableListOf())
        binding.rvCveList.adapter = vulnersAdapter
        vulnersAdapter.notifyDataSetChanged()

    }



    override fun onClick(ports: Ports) {
        binding.tvService.text = ports.service.product
        binding.tvServiceVersion.text = ports.service.version
        binding.service.visibility = View.VISIBLE
        binding.serviceVersion.visibility = View.VISIBLE
        vulnersAdapter = VulnersAdapter(ports.vulners)
        vulnersAdapter.notifyDataSetChanged()
        binding.rvCveList.adapter = vulnersAdapter
    }

}