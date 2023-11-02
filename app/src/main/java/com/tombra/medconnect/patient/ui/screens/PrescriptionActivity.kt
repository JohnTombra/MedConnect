package com.tombra.medconnect.patient.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


import androidx.recyclerview.widget.LinearLayoutManager
import com.tombra.medconnect.data.repository.Repository
import com.tombra.medconnect.databinding.FragmentPrescriptionBinding
import com.tombra.medconnect.patient.ui.adapters.PrescriptionAdapter


class PrescriptionActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context: Context = this


        val repository = Repository()

        val connectId = intent.extras!!.getString("connectId")!!

        val prescriptionAdapter = PrescriptionAdapter{
//            val treatmentLink  = "${list[it].request.time}_${list[it].request.client}_${list[it].request.connectedTo}"
//            val action = TreatmentListFragmentDirections.actionTreatmentListFragmentToTreatmentFragment(treatmentLink)
//            navController.navigate(action)
        }





        binding.prescriptionRecycler.apply {
            adapter = prescriptionAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }


        repository.getConnectPrescriptions(connectId){
            prescriptionAdapter.submitList(it.reversed())
        }



    }



  /*  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {




        val prescriptionAdapter = PrescriptionAdapter(requireContext()){
//            val treatmentLink  = "${list[it].request.time}_${list[it].request.client}_${list[it].request.connectedTo}"
//            val action = TreatmentListFragmentDirections.actionTreatmentListFragmentToTreatmentFragment(treatmentLink)
//            navController.navigate(action)
        }




        binding.prescriptionRecycler.apply {
            adapter = prescriptionAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }


        viewModel.getAllPrescriptions{
            prescriptionAdapter.submitList(it.reversed())
        }




        super.onViewCreated(view, savedInstanceState)
    }

*/

}