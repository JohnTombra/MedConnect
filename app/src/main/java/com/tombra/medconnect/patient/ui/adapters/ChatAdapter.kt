package com.tombra.medconnect.patient.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tombra.medconnect.data.model.Message
import com.tombra.medconnect.databinding.ChatsBinding

class ChatAdapter() :
    ListAdapter<Message, ChatAdapter.ChatViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ChatViewHolder {

        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ChatsBinding.inflate(inflater, viewGroup, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {





//        with(holder) {
//            with(getItem(position)) {
//
//                binding.textLeftCard.isVisible = false
//                binding.textRightCard.isVisible = false
//                binding.textRight.text = ""
//                binding.textLeft.text = ""
//
//
//
//                val text = message
//
//                if(sender == userId){
//                    binding.textRightCard.isVisible = true
//                    binding.textRight.text = text
//                }else{
//                    binding.textLeftCard.isVisible = true
//                    binding.textLeft.text = text
//                }
//
//            }
//        }

    }


    class DiffCallBack() : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message) =
            oldItem.timeStamp == newItem.timeStamp

        override fun areContentsTheSame(oldItem: Message, newItem: Message) =
            oldItem == newItem
    }

    inner class ChatViewHolder(val binding: ChatsBinding) : RecyclerView.ViewHolder(binding.root){


        init{
            binding.root.setOnClickListener {
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION){

                }
            }
        }


    }

}