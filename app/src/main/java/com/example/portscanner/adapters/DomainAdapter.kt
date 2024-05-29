package com.example.portscanner.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.portscanner.R
import com.example.portscanner.databinding.DomainItemsBinding
import com.scanner.scanner.model.DomainCVE

class DomainAdapter(private val context: Context, private val domainCVE: DomainCVE): RecyclerView.Adapter<DomainAdapter.DomainViewHolder>() {

    class DomainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = DomainItemsBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.domain_items, parent, false)
        return DomainViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: DomainViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

