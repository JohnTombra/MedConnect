package com.tombra.medconnect.patient.ui.screens

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.tombra.medconnect.data.repository.Repository
import com.tombra.medconnect.databinding.ActivityProfileBinding


class ProfileActivity: AppCompatActivity() {


    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?)
         {
             super.onCreate(savedInstanceState)
             binding = ActivityProfileBinding.inflate(layoutInflater)
             setContentView(binding.root)


             val repository = Repository()





             repository.getProfile { profile ->
                 binding.firstName.setText("First name: " +profile.firstName)
                 binding.lastName.setText("Last name: "+profile.lastName)
                 binding.phoneNumber.setText("Phone number: "+if(profile.phoneNumber == ""){"Phone number"}else{profile.phoneNumber})
                 binding.email.setText("Email: "+profile.email)
             }








//        var edit1 = false
//
//        binding.edit1.setOnClickListener {
//            if(edit1){
//                edit1 = false
//                //save here and disable
//                viewModel.updateProfile(specialties, binding.firstName.text.toString(), binding.lastName.text.toString(), binding.phoneNumber.text.toString(), binding.email.text.toString(), binding.nin.text.toString(), binding.dateOfBirth.text.toString(),binding.gender.text.toString(),{}){
//                    binding.firstName.isEnabled = false
//                    binding.lastName.isEnabled = false
//                    binding.phoneNumber.isEnabled = false
//                    binding.nin.isEnabled = false
//                    binding.gender.isEnabled = false
//                    binding.chooseProfilePicture.isVisible = false
//                }
//            }else{
//                edit1 = true
//                binding.firstName.isEnabled = true
//                binding.lastName.isEnabled = true
//                binding.phoneNumber.isEnabled = true
//                binding.nin.isEnabled = true
//                binding.gender.isEnabled = true
//                binding.chooseProfilePicture.isVisible = true
//            }
//        }







//        binding.premium.setOnClickListener {
//            if(viewModel.providerId.isBlank() || viewModel.subtype.isBlank()) {
//                Toast.makeText(requireContext(),"Preparing", Toast.LENGTH_SHORT)
//                return@setOnClickListener}
//
//            val data = arrayOf(viewModel.providerId,viewModel.subtype)
//
//            val action = ProfileFragmentDirections.actionProfileFragmentToPremiumHomePage(data)
//            navController.navigate(action)
//        }



//        binding.chooseProfilePicture.setOnClickListener {
//
//            image = 1
//            getImage.launch("image/*")
//
//        }




        var edit2 = false
//
//        binding.edit2.setOnClickListener {
//            if(edit2){
//                edit2 = false
//                //save here
//                viewModel.updateProfile(specialties, binding.firstName.text.toString(), binding.lastName.text.toString(), binding.phoneNumber.text.toString(), binding.email.text.toString(), binding.nin.text.toString(), binding.dateOfBirth.text.toString(),binding.gender.text.toString(),{}){
//                    binding.dentistry.isEnabled = false
//                    binding.dermatology.isEnabled = false
//                    binding.gynacology.isEnabled = false
//                    binding.genetics.isEnabled = false
//                    binding.neurology.isEnabled = false
//                    binding.psychiatry.isEnabled = false
//                    binding.paediatry.isEnabled = false
//                    binding.ophthalmology.isEnabled = false
//                    binding.publicHealth.isEnabled = false
//                    binding.respirology.isEnabled = false
//                    binding.haematology.isEnabled = false
//                    binding.endocrinology.isEnabled = false
//                    binding.urology.isEnabled = false
//                    binding.orthopedix.isEnabled = false
//                    binding.orthchinolaryngology.isEnabled = false
//                    binding.surgery.isEnabled = false
//                }
//
//
//            }else{
//                edit2 = true
//
//                binding.dentistry.isEnabled = true
//                binding.dermatology.isEnabled = true
//                binding.gynacology.isEnabled = true
//                binding.genetics.isEnabled = true
//                binding.neurology.isEnabled = true
//                binding.psychiatry.isEnabled = true
//                binding.paediatry.isEnabled = true
//                binding.ophthalmology.isEnabled = true
//                binding.publicHealth.isEnabled = true
//                binding.respirology.isEnabled = true
//                binding.haematology.isEnabled = true
//                binding.endocrinology.isEnabled = true
//                binding.urology.isEnabled = true
//                binding.orthopedix.isEnabled = true
//                binding.orthchinolaryngology.isEnabled = true
//                binding.surgery.isEnabled = true
//
//                //enable fields
//
//            }
//        }



//        binding.logout.setOnClickListener {
//
//            /// clear auth state here
//            val auth = Auth(1, false, false,"","","","","")
//
//            viewModel.logOut(auth)
//
//
//  }



    }



    var image = 0
  //  private var mStorageref: StorageReference? = null


//    private val getImage =
//        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//            when (image) {
//                1 -> {
//
//                    Glide.with(requireContext()).load(uri).centerCrop().fitCenter()
//                        .into(binding.profilePicture)
//
//                    if (uri != null) {
//
//
//
//                        val savedas2 = System.currentTimeMillis().toString() + ""
//                        val fileReference = mStorageref!!.child(savedas2)
//                        fileReference.putFile(uri)
//                            .addOnSuccessListener {
//                                mStorageref!!.child(savedas2).downloadUrl.addOnSuccessListener { uri ->
//                                    val documentPath1 = uri.toString()
//                                    viewModel.imageUrl = documentPath1
//                                }
//                            }.addOnFailureListener {
//                                Toast.makeText(context, "Image Upload Failed", Toast.LENGTH_SHORT).show()
//                            }
//
//
//                    }else{
//                        Toast.makeText(context,"Add an image", Toast.LENGTH_SHORT).show()
//                    }
//
//                    //uploadImage
//                }
//            }
//        }



}