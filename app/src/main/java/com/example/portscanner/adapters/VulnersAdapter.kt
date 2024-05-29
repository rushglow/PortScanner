package com.example.portscanner.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.portscanner.R
import com.example.portscanner.databinding.VulnersItemBinding
import com.scanner.scanner.model.Vulners

class VulnersAdapter(private val vulners: List<Vulners>): RecyclerView.Adapter<VulnersAdapter.VulnersViewHolder>() {

    class VulnersViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = VulnersItemBinding.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VulnersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.vulners_item, parent, false)
        return VulnersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VulnersViewHolder, position: Int) {
        holder.binding.tvVulnerName.text = vulners[position].vulnersName
        holder.binding.tvCvss.text = vulners[position].cvss
    }

    override fun getItemCount(): Int {
        return vulners.size
    }

}
