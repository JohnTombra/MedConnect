package com.tombra.medconnect.patient.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.tombra.medconnect.data.model.ConnectAndMedicalPersonnel
import com.tombra.medconnect.data.repository.Repository
import com.tombra.medconnect.databinding.ConnectBinding


class ConnectsAdapter(val callback: (Int)-> Unit) :
    ListAdapter<ConnectAndMedicalPersonnel, ConnectsAdapter.ChatViewHolder>(DiffCallBack()) {

    lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ChatViewHolder {
        context = viewGroup.context
        val inflater = LayoutInflater.from(context)
        val binding = ConnectBinding.inflate(inflater, viewGroup, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {



        with(holder) {
            with(getItem(position)) {

                Glide.with(context).load(medicalPersonnel.picture).centerCrop().into(binding.image)
                binding.name.text = "${medicalPersonnel.firstName} ${medicalPersonnel.lastName}"
                binding.serviceType.text = "${medicalPersonnel.department}"
                binding.category.text = "${medicalPersonnel.subDepartment}"

                binding.dateAndTime.text = "2/10/2023" //myDateFormatter(request.time)

                FirebaseDatabase.getInstance().reference.child("patients/${Repository._patientId}/profile/picture").get().addOnSuccessListener {
                    if(it.exists()){
                        Glide.with(context).load(it.getValue(String::class.java)).centerCrop().into(binding.image2)
                    }
                }

            }
        }
    }


    class DiffCallBack() : DiffUtil.ItemCallback<ConnectAndMedicalPersonnel>() {
        override fun areItemsTheSame(oldItem: ConnectAndMedicalPersonnel, newItem: ConnectAndMedicalPersonnel) =
            oldItem.connect.medicalPersonnelId == newItem.connect.medicalPersonnelId

        override fun areContentsTheSame(oldItem: ConnectAndMedicalPersonnel, newItem: ConnectAndMedicalPersonnel) =
            oldItem == newItem
    }

    inner class ChatViewHolder(val binding: ConnectBinding) : RecyclerView.ViewHolder(binding.root){


        init{
            binding.root.setOnClickListener {
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                        callback(position)
                }
            }
        }


    }

}