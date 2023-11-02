package com.tombra.medconnect.patient.ui.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tombra.medconnect.R
import com.tombra.medconnect.data.model.ConnectAndMedicalPersonnel
import com.tombra.medconnect.data.model.MedicalPersonnel
import com.tombra.medconnect.data.repository.Repository
import com.tombra.medconnect.databinding.ActivityConnectsBinding
import com.tombra.medconnect.patient.ui.adapters.ConnectsAdapter

class ConnectsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityConnectsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context: Context = this

        val repository = Repository()


        var connects = listOf<ConnectAndMedicalPersonnel>()

        val connectsAdapter = ConnectsAdapter{
            val item = connects[it]
            startActivity(Intent(context, ConnectDashboard::class.java).putExtra("connectId",item.connect.connectId))
        }


        binding.connectsRecycler.apply {
            adapter = connectsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }


        repository.getAllConnects {
             connects = it
            connectsAdapter.submitList(it)
        }



    }
}