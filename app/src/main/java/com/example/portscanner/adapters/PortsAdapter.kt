package com.example.portscanner.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.portscanner.DomainActivity
import com.example.portscanner.R
import com.example.portscanner.databinding.PortItemBinding
import com.scanner.scanner.model.Ports

class PortsAdapter(val listener: OnClickListener, private val ports: MutableList<Ports>): RecyclerView.Adapter<PortsAdapter.PortsViewHolder>() {

    class PortsViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = PortItemBinding.bind(item)

        fun bind(port: Ports, listener: OnClickListener) = with(binding){
            itemView.setOnClickListener {
                listener.onClick(port)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.port_item, parent, false)
        return PortsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ports.size
    }

    override fun onBindViewHolder(holder: PortsViewHolder, position: Int) {
        holder.bind(ports[position], listener)
        holder.binding.tvOpenPorts.text = ports[position].port
    }


    // onClickListener Interface
    interface OnClickListener {
        fun onClick(ports: Ports)
    }
}
