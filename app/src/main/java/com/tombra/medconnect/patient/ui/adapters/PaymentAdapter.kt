package com.tombra.medconnect.patient.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tombra.medconnect.data.model.Payment
import com.tombra.medconnect.databinding.PaymentBinding

class PaymentAdapter(val callback:(Int) -> Unit) :
    ListAdapter<Payment, PaymentAdapter.MessageViewHolder>(DiffCallBack()) {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = PaymentBinding.inflate(inflater, viewGroup, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {


                binding.price.text = amount.toString()
                binding.description.text = description
//                binding.createdAt.text = myDateFormatter(timestamp)
                binding.waiting.isVisible = false
                binding.payType.isVisible = false
                binding.pay.isEnabled = false




                if(paid){
                    binding.paymentTime.isVisible = true
//                    binding.paymentTime.text = "Payment time: ${myDateFormatter(paymentTime!!)}"
                    binding.pay.isEnabled = false
                    binding.pay.text = "Paid"
                    binding.payType.isVisible = false
                    binding.waiting.isVisible = false
                    binding.pay.setBackgroundColor(Color.LTGRAY)

                }else{
                    binding.pay.isEnabled = true

                    binding.pay.setOnClickListener {
                        binding.payType.isVisible = true
                    }
                }

            }
        }
    }

    class DiffCallBack() : DiffUtil.ItemCallback<Payment>() {
        override fun areItemsTheSame(oldItem: Payment, newItem: Payment) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Payment, newItem: Payment) =
            oldItem == newItem
    }


    inner class MessageViewHolder(val binding: PaymentBinding) : ViewHolder(binding.root){

        init {

            binding.root.setOnClickListener {
                val position: Int = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                callback(position)
                }
            }

        }
    }



}