package com.example.portscanner.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.portscanner.R
import com.example.portscanner.databinding.DomainItemsBinding
import com.scanner.scanner.model.DomainCVE

class DomainAdapter(val listener: OnClickListener, private val domain: MutableList<DomainCVE>, private val load: Boolean): RecyclerView.Adapter<DomainAdapter.DomainViewHolder>() {

    class DomainViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = DomainItemsBinding.bind(item)

        fun bind(domain: DomainCVE, listener: OnClickListener) = with(binding){
            itemView.setOnClickListener {
                listener.onClick(domain)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.domain_items, parent, false)
        return DomainViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return domain.size
    }

    override fun onBindViewHolder(holder: DomainViewHolder, position: Int) {
        holder.binding.tvDomainName.text = domain[position].address
        holder.bind(domain[position], listener)
        if (load){
            holder.binding.ivDone.visibility = View.INVISIBLE
            holder.binding.ivLoad.visibility = View.VISIBLE
        }else{
            holder.binding.ivDone.visibility = View.VISIBLE
            holder.binding.ivLoad.visibility = View.INVISIBLE
        }
    }

    interface OnClickListener {
        fun onClick(domain: DomainCVE)
    }
}

