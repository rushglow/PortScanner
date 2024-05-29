package com.example.portscanner

import android.app.Dialog
import android.app.ProgressDialog
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
    lateinit var mService: DomainService
    lateinit var portAdapter: PortsAdapter
    lateinit var vulnersAdapter: VulnersAdapter
    lateinit var binding: ActivityDomainBinding
    lateinit var domainCVE: DomainCVE


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val address = intent.extras!!.getString("address")
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val cveLayoutManager = LinearLayoutManager(this)
        binding = ActivityDomainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvOpenPorts.layoutManager = layoutManager
        binding.rvCveList.layoutManager = cveLayoutManager
        mService = Common.domainService

        portAdapter = PortsAdapter(this, mutableListOf<Ports>(Ports(1,"12","tcp",
            mutableListOf<Vulners>(), Serv(11, "serv", "prod", "vers"))))
        portAdapter.notifyDataSetChanged()
        vulnersAdapter = VulnersAdapter(mutableListOf(Vulners(1, "9.0", "Vuln", "true")))
        vulnersAdapter.notifyDataSetChanged()

        getData(address!!)

    }

     fun getData(address: String) {
        mService.getDomainCVE(Address(address)).enqueue(object : Callback<DomainCVE> {

            override fun onFailure(p0: Call<DomainCVE>, p1: Throwable) {
                Toast.makeText(applicationContext, "WRONG DOMAIN PLS BACK", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(p0: Call<DomainCVE>, response: Response<DomainCVE>) {
                domainCVE = response.body()!!
                portAdapter = PortsAdapter(portAdapter.listener, response.body()!!.openPorts)
                portAdapter.notifyDataSetChanged()
                binding.tvDomainName.text = domainCVE.address
                binding.rvOpenPorts.adapter = portAdapter
            }
        })
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