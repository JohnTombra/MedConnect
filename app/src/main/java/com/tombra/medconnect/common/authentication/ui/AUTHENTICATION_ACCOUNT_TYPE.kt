package com.tombra.medconnect.common.authentication.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.firebase.database.FirebaseDatabase
import com.tombra.medconnect.R
import com.tombra.medconnect.data.model.Admin
import com.tombra.medconnect.data.model.Auth
import com.tombra.medconnect.data.model.Signup
import com.tombra.medconnect.data.model.User
import com.tombra.medconnect.data.repository.Repository
import com.tombra.medconnect.medicalpersonnel.ui.screens.Home
import com.tombra.medconnect.patient.ui.screens.MainActivity

class AUTHENTICATION_ACCOUNT_TYPE : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication_account_type)



        val loadingScreen = findViewById<ConstraintLayout>(R.id.loadingScreen)
        //check is local is set

        loadingScreen.isVisible = true
        val context: Context = this

        val repository = Repository(context)

        val accountType = repository.getAuthInfo().accountType
        if(accountType.isBlank()){
            loadingScreen.isVisible = false

            val user = findViewById<CardView>(R.id.userCard)
            val admin = findViewById<CardView>(R.id.adminCard)
            val network: FirebaseDatabase = FirebaseDatabase.getInstance()

            user.setOnClickListener {
                loadingScreen.isVisible = true
//                //get the

                network.reference.child("Accounts").child(repository.getAuthInfo().authId).get().addOnSuccessListener {
                    val result = it.getValue(Signup::class.java)

                    val user = User(
                        repository.getAuthInfo().authId,
                        result!!.firstName!!,
                        result!!.lastName!!,
                        "https://firebasestorage.googleapis.com/v0/b/casatopia-c2993.appspot.com/o/images%2F1689783721607?alt=media&token=43f4e898-771c-434c-876f-090d86ce46d7",
                        "Users",
                        email =  result!!.email!!,
                        phoneNumber =  result!!.phoneNumber!!,
                    )





                    network.reference.child("Users").child(repository.getAuthInfo().authId).child("Profile").setValue(user).addOnSuccessListener {

                        val auth = Auth(
                            authenticated = "true",
                            authId = repository.getAuthInfo().authId,
                            accountType = "Users"
                        )
                        repository.saveAuthInfo(auth)



                        val signUp =  Signup(
                            repository.getAuthInfo().authId,
                            true,
                            "Users",
                            result.firstName,
                            result.lastName,
                            result.email,
                            result.phoneNumber,
                        )

                        network.reference.child("Accounts").child(repository.getAuthInfo().authId).setValue(signUp).addOnSuccessListener {
                            startActivity(Intent(context,MainActivity::class.java))
                            Toast.makeText(context,"Account created", Toast.LENGTH_SHORT).show()
                        }
                        //also update accounts

                        //go to admin home
                    }

                }



            }

            admin.setOnClickListener {


                network.reference.child("Accounts").child(repository.getAuthInfo().authId).get().addOnSuccessListener {
                    val result = it.getValue(Signup::class.java)

                    val admin = Admin(
                        repository.getAuthInfo().authId,
                        result!!.firstName!!,
                        result!!.lastName!!,
                        "https://firebasestorage.googleapis.com/v0/b/casatopia-c2993.appspot.com/o/images%2F1689783721607?alt=media&token=43f4e898-771c-434c-876f-090d86ce46d7",
                        "Admins",
                        email =  result.email!!,
                        phoneNumber =  result!!.phoneNumber!!,
                    )

                    network.reference.child("Admins").child(repository.getAuthInfo().authId).child("Profile").setValue(admin).addOnSuccessListener {
                        val repository = Repository(context)
                        val auth = Auth(
                            authenticated = "true",
                            authId = repository.getAuthInfo().authId,
                            accountType = "Admins"
                        )
                        repository.saveAuthInfo(auth)




                        val signUp =  Signup(
                            repository.getAuthInfo().authId,
                            true,
                            "Admins",
                            result.firstName,
                            result.lastName,
                            email =  result!!.email!!,
                            phoneNumber =  result!!.phoneNumber!!,
                        )

                        network.reference.child("Accounts").child(repository.getAuthInfo().authId).setValue(signUp).addOnSuccessListener {
                            Toast.makeText(context,"Account created", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(context, Home::class.java))}
                        //go to admin home
                    }
                }


            }

        }else{


            if(accountType == "Admins"){
                startActivity(Intent(context,Home::class.java ))
            }
            if(accountType == "Users"){
                startActivity(Intent(context,MainActivity::class.java ))
            }
        }



    }


    override fun onStart() {

        super.onStart()

        val context: Context = this
        val mAuth = Repository(context)

        Log.d("SIGN UP", mAuth.getAuthInfo().authenticated)
        if(mAuth.getAuthInfo().authenticated == "false"){
            startActivity(Intent(context, AUTHENTICATION_LOGIN::class.java))
        }else{
        }

    }


//    override fun onBackPressed() {
//        moveTaskToBack(true)
//    }

}