package com.tombra.medconnect.patient.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.tombra.medconnect.data.model.Prescription
import com.tombra.medconnect.databinding.PrescriptionBinding


class PrescriptionAdapter(val callBack: (Int)-> Unit) :
    ListAdapter<Prescription, PrescriptionAdapter.MyViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = PrescriptionBinding.inflate(inflater, viewGroup, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder) {
            with(getItem(position)) {

                binding.titleOfPrescription.text = "Title: $title"
//                binding.createdAt.text = "Created at: ${myDateFormatter(timeStamp)}"
           //     binding.createdBy.text = "$createdBy"
                binding.dosage.text = "Dosage: ${dosage}"
                binding.notes.text = "Notes: ${notes}"


          }
        }
    }

    class DiffCallBack() : DiffUtil.ItemCallback<Prescription>() {
        override fun areItemsTheSame(oldItem: Prescription, newItem: Prescription) =
            oldItem.prescriptionId == newItem.prescriptionId

        override fun areContentsTheSame(oldItem: Prescription, newItem: Prescription) =
            oldItem == newItem
    }

    inner class MyViewHolder(val binding: PrescriptionBinding) : ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    callBack(position)
            }
        }
    }

}