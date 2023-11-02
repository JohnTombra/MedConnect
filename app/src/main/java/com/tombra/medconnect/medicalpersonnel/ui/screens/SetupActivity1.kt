package com.tombra.medconnect.medicalpersonnel.ui.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tombra.medconnect.data.model.MedicalPersonnel
import com.tombra.medconnect.data.repository.Repository
import com.tombra.medconnect.databinding.ActivitySetup1Binding

class SetupActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySetup1Binding.inflate(layoutInflater)
        setContentView(binding.root)



        val context: Context = this


        val repository = Repository()




        binding.submit.setOnClickListener {

            //perform checks

            if(binding.firstName.text.toString().isEmpty()){
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.lastName.text.toString().isEmpty()){
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.gender.text.toString().isEmpty()){
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.phoneNumber.text.toString().isEmpty()){
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.email.text.toString().isEmpty()){
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.field.text.toString().isEmpty()){
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.specialty.text.toString().isEmpty()){
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.locale.text.toString().isEmpty()){
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.state.text.toString().isEmpty()){
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.country.text.toString().isEmpty()){
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val medicalPersonnel = MedicalPersonnel(
                id = "AUTHID",//use auth id later
                firstName = binding.firstName.text.toString(),
                lastName = binding.lastName.text.toString(),
                gender = binding.gender.text.toString(),
                picture = "",
                phoneNumber = binding.phoneNumber.text.toString(),
                email = binding.email.text.toString(),
                department = binding.field.text.toString(),
                subDepartment = binding.specialty.text.toString(),
                locale = binding.locale.text.toString(),
                state = binding.state.text.toString(),
                country = binding.country.text.toString(),
            )



            repository.completeMedicalPersonnelSetup(medicalPersonnel){

                startActivity(Intent(context, Home::class.java ))

            }



        }









    }
}