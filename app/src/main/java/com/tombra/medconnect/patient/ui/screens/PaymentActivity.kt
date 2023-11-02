package com.tombra.medconnect.patient.ui.screens

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tombra.medconnect.data.model.Payment
import com.tombra.medconnect.data.repository.Repository
import com.tombra.medconnect.databinding.PaymentLayoutBinding
import com.tombra.medconnect.patient.ui.adapters.PaymentAdapter
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch



class PaymentActivity: AppCompatActivity() {




    private val request_code = 1

    private var active = true

    lateinit var emergencyId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = PaymentLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        val context: Context = this

        val repository = Repository()


        val connectId = intent.extras!!.getString("connectId")!!

        var paymentList = listOf<Payment>()




//        binding.seeWallet.setOnClickListener {
//            navController.navigate(R.id.action_paymentScreenFragment_to_clientWalletFragment)
//        }




        val paymentAdapter = PaymentAdapter{ position ->

        }

        binding.recycler.apply {
            adapter = paymentAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }


        repository.getConnectPayments(connectId){
            paymentAdapter.submitList(it)
            paymentList = it
        }





    }

//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//
//
//
//
//
//
//
//
////        viewModel.checkTotal(emergencyId) {x,y ->
////            if (active) {
////                viewModel.updateRead(x,y)
////            }
////        }
//
//
//        //start listening for wallet balance here
//
//
//
////        binding.seeWallet.setOnClickListener {
////            navController.navigate(R.id.action_paymentScreenFragment_to_clientWalletFragment)
////        }
//
//
////        val paymentAdapter = PaymentAdapter{ paymentMode, position ->
////            //after paystack stuff call the make payment function
////
////
////
////            //select cash or transfer
////
////            //show cash or online transfer
////
////
////            when(paymentMode){
////                PaymentMode.CASH -> {
////                    viewModel.makePaymentWithCash(paymentList[position])
////                }
////                PaymentMode.TRANSFER -> {
////
////                    viewModel.setOnlinePayment(paymentList[position])
////                    //show flutter wave interface and continue.. using activity for result
////
////                    Log.d("FRAGMENT","Amount: ${paymentList[position].price}")
////
////
////
////
////
////
////
////
////
////                    startActivityForResult(
////                        //add a transfer successful method
////                        Intent(requireContext(), FlutterWavePaymentScreen::class.java)
////                            .putExtra("Amount",paymentList[position].price.toString())
////                            .putExtra("Description",  paymentList[position].description)
////                            .putExtra("FirstName", paymentList[position].clientId + "," + paymentList[position].providerId)
////                            .putExtra("LastName", paymentList[position].emergencyId)
////                    , request_code)
////
////                   // after flutter wave payment do below
////
////                }
////                PaymentMode.WALLET -> {
////                    viewModel.makePaymentWithWallet(paymentList[position])
////                }
////            }
////
////
////        }
////
////        binding.recycler.apply {
////            adapter = paymentAdapter
////            layoutManager = LinearLayoutManager(requireContext())
////            setHasFixedSize(true)
////        }
//
//
//
////        binding.create.setOnClickListener {
////            viewModel.createPayment()
////        }
//
//
//
////        viewModel.getPayments{
////            paymentAdapter.submitList(it)
////            paymentList = it
////        }
//
//
////        viewLifecycleOwner.lifecycleScope.launch {
////            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
////
//////                launch {
//////
//////                    viewModel.uiState.map {
//////                        it.paymentsList
//////                    }.distinctUntilChanged().collect {
//////                        it?.let {
//////                            Log.d("VIEWMODEL", "READING")
//////                            paymentList = it
//////
//////                        }
//////                    }
//////
//////                }
////
////
////                launch {
////
////
////                    viewModel.uiState.map {
////                        it.walletBalance
////                    }.distinctUntilChanged().collect {
////                        it?.let {
////                            Log.d("VIEWMODEL", "READING WALLET BALANCE")
////                            binding.balance.text = "Wallet: $it"
////
////                        }
////                    }
////
////
////                }
////
////            }
////        }
//
//
//
//
//
//
//        super.onViewCreated(view, savedInstanceState)
//    }
//

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == request_code) {
//            if (resultCode == RESULT_OK) {
//                val status = data!!.extras!!.getString("status")
//                Log.d("FRAGMENT", "RESULT $status")
//
//                if (status == "success") {
//                    viewModel.makePaymentWithTransfer()
//                }
//
//            }
//        }
//
//    }


    override fun onPause() {
        active = false
        super.onPause()
    }



    override fun onResume() {
        super.onResume()
        active = true

//        viewModel.checkTotalOnce(emergencyId) {x,y ->
//            if (active) {
//                viewModel.updateRead(x,y)
//            }
//        }
    }

}