package com.tombra.medconnect.patient.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tombra.medconnect.R
import com.tombra.medconnect.databinding.ActivityConnectDashboardBinding


class ConnectDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityConnectDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val connectId = intent.extras!!.getString("connectId")

        val context: Context = this



        binding.chat.setOnClickListener {
            startActivity(Intent(context, ChatActivity::class.java).putExtra("connectId", connectId))
        }

        binding.payment.setOnClickListener {
            startActivity(Intent(context, PaymentActivity::class.java).putExtra("connectId", connectId))
        }



        binding.prescriptions.setOnClickListener {
            startActivity(Intent(context, PrescriptionActivity::class.java).putExtra("connectId", connectId))

        }




        binding.call.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:081")))
        }

        binding.providerImage.setOnClickListener {

        }

        binding.clientImage.setOnClickListener {

        }

        binding.location.setOnClickListener {
            //show map view
        }



    }
}