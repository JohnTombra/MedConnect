package com.tombra.medconnect.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tombra.medconnect.data.model.*

class Repository(val context: Context) {


    val network = FirebaseDatabase.getInstance().reference


    private val localSet: SharedPreferences.Editor =
        context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE).edit()
    private val localGet: SharedPreferences =
        context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)


    companion object{
        val _patientId: String = ""
        val _medicalPersonnelId: String = ""
    }



    fun saveAuthInfo(auth: Auth) {
        localSet.putString("Authenticated", auth.authenticated.toString())
        localSet.putString("AuthId", auth.authId)
        localSet.putString("AuthType", auth.accountType)
        localSet.apply()
    }


    fun getAuthInfo(): Auth {
        val authenticated = localGet.getString("Authenticated", "false").toString()
        val authId = localGet.getString("AuthId", "").toString()
        val authType = localGet.getString("AuthType", "").toString()
        return Auth(authenticated, authId, authType)
    }



    var allMedicalPersonnels = listOf<MedicalPersonnel>()



    fun getAllMedicalPersonnels(callback: (List<MedicalPersonnel>)-> Unit){
        network.child("MedicalPersonnels").get().addOnSuccessListener {

            if(it.exists()){
                allMedicalPersonnels = it.children.map { it.getValue(MedicalPersonnel::class.java)!! }
                callback(allMedicalPersonnels)
            }
        }
    }






    fun searchForMedicalPersonnel(query: String, callback: (List<MedicalPersonnel>) -> Unit) {


        if(query.isEmpty()){
            callback(allMedicalPersonnels)
        }

        callback(allMedicalPersonnels.filter {
            it.active && it.firstName.contains(query) || it.lastName.contains(
                query
            ) || it.department.contains(query) || it.subDepartment.contains(query) || it.locale.contains(
                query
            ) || it.state.contains(query) || it.country.contains(query)
        })

    }



    fun getAllConnects(callback: (List<ConnectAndMedicalPersonnel>) -> Unit){
        var list = listOf<ConnectAndMedicalPersonnel>()
        getConnects {
            val all = it
            var size = all.size
            all.forEach {
                val connect = it
                size--
                getConnectMedicalPersonnelId(it.medicalPersonnelId){
                    getConnectMedicalPersonnel(it){
                        list + ConnectAndMedicalPersonnel(connect, it)
                    }
                    if(size == 0) callback(list)
                }
            }
        }
    }



    fun getConnects(callback:(List<Connect>) -> Unit){
        //should be treatment id and medical personnel
        network.child("patients/$_patientId/connects").get().addOnSuccessListener {

            if(it.exists()){

                network.child("core/connects/${it.getValue(String::class.java)}").get().addOnSuccessListener {
                    if(it.exists()){
                        callback(it.children.map { it.getValue(Connect::class.java)!! })
                    }else{
                        callback(listOf())
                    }
                }

            }




        }
    }

    fun getConnectMedicalPersonnelId(connectId: String, callback: (String) -> Unit){
        network.child("patients/$_patientId/connects/medicalPersonnelId").get().addOnSuccessListener {
            if(it.exists()){
                callback(it.getValue(String::class.java)!!)
            }else{
                callback("")
            }
        }
    }

    fun getConnectMedicalPersonnel(medicalPersonnelId: String, callback: (MedicalPersonnel) -> Unit){

        network.child("medicalPersonnels/$medicalPersonnelId/profile").get().addOnSuccessListener {
            if(it.exists()){
                callback(it.getValue(MedicalPersonnel::class.java)!!)
            }else{
                callback(MedicalPersonnel())
            }
        }

    }


    fun getConnectPayments(connectId: String, callback: (List<Payment>) -> Unit){
        network.child("$_patientId/connects/$connectId/payments").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    callback(snapshot.children.map { it.getValue(Payment::class.java)!! })
                }else{
                    callback(listOf())
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


    fun getConnectChats(connectId: String, callback: (List<Message>) -> Unit){
        network.child("$_patientId/connects/$connectId/chats").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    callback(snapshot.children.map { it.getValue(Message::class.java)!! })
                }else{
                    callback(listOf())
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


    fun sendMessage(connectId: String,message: Message, callback:()->Unit){
        network.child("$_patientId/connects/$connectId/chats/${message.timeStamp}").setValue(message).addOnSuccessListener {
            callback()
        }
    }

    fun getConnectPrescriptions(connectId: String, callback: (List<Prescription>) -> Unit){
        network.child("$_patientId/connects/$connectId/prescriptions").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    callback(snapshot.children.map { it.getValue(Prescription::class.java)!! })
                }else{
                    callback(listOf())
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }



    fun getMedicalRecord(){
        network.child("patients/$_patientId/medicalRecord").get().addOnSuccessListener {
            if(it.exists()){

            }else{

            }
        }
    }


    fun getProfile(callback: (Profile) -> Unit){

        network.child("patients/$_patientId/profile").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    callback(snapshot.getValue(Profile::class.java)!!)
                }else{
                    callback(Profile())
                }


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }



    /////create account

    fun completeSetup(medicalPersonnel: MedicalPersonnel, callback: () -> Unit){
        network.child("medicalPersonels/${medicalPersonnel.id}/profile").setValue(medicalPersonnel).addOnSuccessListener {
            callback()
        }
    }



    fun medicalPersonnelProfile(id: String, callback: (MedicalPersonnel) -> Unit){
        network.child("medicalPersonnels/$id/profile").get().addOnSuccessListener {
            if(it.exists()){
                callback(it.getValue(MedicalPersonnel::class.java)!!)
            }
        }
    }

    fun medicalPersonnelConnects(){

    }



    fun _getAllConnects(callback: (List<ConnectAndPatient>) -> Unit){
        var list = listOf<ConnectAndPatient>()
        _getConnects {
            val all = it
            var size = all.size
            all.forEach {
                val connect = it
                size--
                _getConnectPatientId(it.patientId){
                    _getConnectPatient(it){
                        list + ConnectAndPatient(connect, it)
                    }
                    if(size == 0) callback(list)
                }
            }
        }
    }



    fun _getConnects(callback:(List<Connect>) -> Unit){
        //should be treatment id and medical personnel
        network.child("medicalPersonnels/$_medicalPersonnelId/connects").get().addOnSuccessListener {

            if(it.exists()){

                network.child("core/connects/${it.getValue(String::class.java)}").get().addOnSuccessListener {
                    if(it.exists()){
                        callback(it.children.map { it.getValue(Connect::class.java)!! })
                    }else{
                        callback(listOf())
                    }
                }

            }




        }
    }

    fun _getConnectPatientId(connectId: String, callback: (String) -> Unit){
        network.child("patients/$_patientId/connects/patientId").get().addOnSuccessListener {
            if(it.exists()){
                callback(it.getValue(String::class.java)!!)
            }else{
                callback("")
            }
        }
    }


    fun _getConnectPatient(patientId: String, callback: (Patient) -> Unit){

        network.child("patients/$patientId/profile").get().addOnSuccessListener {
            if(it.exists()){
                callback(it.getValue(Patient::class.java)!!)
            }else{
                callback(Patient())
            }
        }

    }



    fun allRequests(callback: (List<RequestAndPatient>) -> Unit){
        network.child("medicalPersonnels/$_medicalPersonnelId/requests").get().addOnSuccessListener {
            if(it.exists()){

                val list = listOf<RequestAndPatient>()
                it.children.map {
                    it.getValue(Request::class.java)!!
                }.forEach {
                    patientFromRequest(it){
                        list + it
                    }
                }

                callback(list)

            }
        }
    }



    fun patientFromRequest(request: Request, callback: (RequestAndPatient) -> Unit){

        network.child("patients/${request.patientId}/profile").get().addOnSuccessListener {
            if(it.exists()){
                val patient = it.getValue(Patient::class.java)!!
                val request = request
                callback(RequestAndPatient(request,patient))
            }
        }

    }



    fun previewRequests(request: Request, callback: (RequestAndPatient) -> Unit){
        network.child("medicalPersonnels/$_medicalPersonnelId/requests/${request.requestId}").get().addOnSuccessListener {
            if(it.exists()){
                patientFromRequest(request){
                    callback(it)
                }
            }}
    }



    fun acceptPreview(connect: Connect, callback: () -> Unit){
        //after accepting it should be removed from requests list
        network.child("core/connects/${connect.timestamp}").setValue(connect).addOnSuccessListener {
            network.child("patients/${connect.patientId}/connects/${connect.timestamp}").setValue(connect.timestamp).addOnSuccessListener {
                network.child("medicalPersonnels/${connect.patientId}/connects/${connect.timestamp}").setValue(connect.timestamp).addOnSuccessListener {
                    callback()
                }
            }
        }
    }




    fun completeMedicalPersonnelSetup(medicalPersonnel: MedicalPersonnel, callback: () -> Unit){

        //save auth id locally and save account type locally
        //save setup info to network

    }






}