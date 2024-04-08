package com.paulus.studentapp.view

import android.icu.util.TimeUnit
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.paulus.studentapp.R
import com.paulus.studentapp.databinding.FragmentStudentDetailBinding
import com.paulus.studentapp.viewmodel.DetailViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val studentId = arguments?.getString("id")
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(studentId)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.student.observe(viewLifecycleOwner, Observer {student ->
            //val picasso = Picasso.Builder(requireContext())
            //picasso.listener { picasso, uri, exception ->
                //exception.printStackTrace()
            //}
            Picasso.get().load(student.photoUrl).into(binding.imgStudentDetail)
            binding.txtID.setText(student.id)
            binding.txtName.setText(student.name)
            binding.txtBod.setText(student.dob)
            binding.txtPhone.setText(student.phone)

            binding.btnUpdate?.setOnClickListener {
                Observable.timer(5, java.util.concurrent.TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.ic_launcher_foreground)
                    }
            }

            binding.btnBack.setOnClickListener{
                val action = StudentDetailFragmentDirections.actionStudentListFragment()
                Navigation.findNavController(it).navigate(action)
            }
        })
    }
}