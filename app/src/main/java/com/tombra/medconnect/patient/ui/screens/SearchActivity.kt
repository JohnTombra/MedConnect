package com.tombra.medconnect.patient.ui.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.tombra.medconnect.R
import com.tombra.medconnect.data.model.ConnectAndMedicalPersonnel
import com.tombra.medconnect.data.repository.Repository
import com.tombra.medconnect.databinding.ActivitySearchBinding
import com.tombra.medconnect.patient.ui.adapters.ConnectsAdapter
import com.tombra.medconnect.patient.ui.adapters.SearchAdapter

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val context: Context = this
        val repository = Repository()






        //add text change listener


        var connects = listOf<ConnectAndMedicalPersonnel>()

        val searchAdapter = SearchAdapter{
            val item = connects[it]
            //goto send treatment request activity
            //see the medics pofile
            //see country
            //see consultation price
            //see location
            //see if verified
            //see map
            //click send treatment request
        }


        binding.searchRecycler.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }



        repository.searchForMedicalPersonnel(""){

            searchAdapter.submitList(it)

        }


        binding.searchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if(binding.searchBox.text == null) return
                if(binding.searchBox.text.toString().isEmpty()) return


                repository.searchForMedicalPersonnel(binding.searchBox.text.toString()){

                    searchAdapter.submitList(it)

                }


            }
        })

    }
}