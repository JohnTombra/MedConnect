package com.tombra.medconnect.patient.ui.screens

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tombra.medconnect.data.model.Message
import com.tombra.medconnect.data.repository.Repository
import com.tombra.medconnect.databinding.ActivityChatBinding
import com.tombra.medconnect.patient.ui.adapters.ChatAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatActivity : AppCompatActivity() {
    private lateinit var context: Context
    var active = false

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this


        val repository = Repository()
        ///initialize all views

//save to admin node also, so they can access it, transfer the estate id so it is pinned
        val chatsRecycler = binding.recycler
        val send =  binding.sendButton
        val textBox =  binding.textBox
        val logo =  binding.logo





        val adminName =  binding.username
        val adminImage =  binding.profilePic



        val connectId = intent.extras!!.getString("connectId").toString()
        val medicalPersonnelId = intent.extras!!.getString("medicalPersonnelId").toString()



        val chatsAdapter = ChatAdapter()
        chatsRecycler.apply {
            adapter = chatsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }





        repository.getConnectChats(connectId){ messages ->
            chatsAdapter.submitList(messages)
        }




        repository.getConnectMedicalPersonnel(medicalPersonnelId){ admin ->
             CoroutineScope(Dispatchers.Main).launch {
                adminName.text = "${admin.firstName} ${admin.lastName}"
                Glide.with(context).load(admin.picture)
                    .fitCenter()
                    .centerCrop()
                    .into(adminImage)
            }
        }



        send.setOnClickListener {

            if(textBox.text.isBlank()){
                return@setOnClickListener
            }

            send.isEnabled = false
            textBox.isEnabled = false
            send.isVisible = false

            val message = Message(
                timeStamp = System.currentTimeMillis().toString(),
                message = textBox.text.toString(),
            )

            repository.sendMessage(connectId, message){
                send.isEnabled = true
                textBox.isEnabled = true
                textBox.setText("")
                send.isVisible = true
            }
        }



//
//        userDatabase.checkTotal(adminId) {x,y ->
//            if (active) {
//                userDatabase.updateRead(x,y)
//            }
//        }




//
//
//


    } //transactions layout should contain
    //description of service offerred and the price and a button for pay and



    override fun onStart() {
        super.onStart()
        active = true
    }




    override fun onBackPressed() {
        super.onBackPressed()
        active = false
    }


    //if page is active set read as same if total changes


}