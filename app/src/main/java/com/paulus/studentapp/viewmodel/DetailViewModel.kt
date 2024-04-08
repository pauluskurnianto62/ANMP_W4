package com.paulus.studentapp.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.paulus.studentapp.model.Student
import kotlin.coroutines.coroutineContext

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "DetailViewModel"

    private val students = MutableLiveData<Student>()
    val student: LiveData<Student>
        get() = students

    fun fetch(studentId: String?) {
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val gson = Gson()
                val student = gson.fromJson(response.toString(), Student::class.java)
                students.value = student
            },
            Response.ErrorListener { error ->
                Log.e(TAG, "Error fetching student detail: ${error.message}")
            })

        Volley.newRequestQueue(getApplication<Application>().applicationContext).add(request)
    }
}
