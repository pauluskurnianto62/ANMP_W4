package com.paulus.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulus.studentapp.R
import com.paulus.studentapp.databinding.FragmentFoodListBinding
import com.paulus.studentapp.databinding.FragmentStudentListBinding
import com.paulus.studentapp.viewmodel.FoodViewModel
import com.paulus.studentapp.viewmodel.ListViewModel


class FoodListFragment : Fragment() {
    private lateinit var viewModel: FoodViewModel
    private val foodListAdapter  = FoodListAdapter(arrayListOf())
    private lateinit var binding: FragmentFoodListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = foodListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.foodLD.observe(viewLifecycleOwner, Observer {
            foodListAdapter.updateFoodList(it)
        })

        viewModel.foodLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtErrors?.visibility = View.VISIBLE
            } else {
                binding.txtErrors?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoads.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoads.visibility = View.GONE
            }
        })
    }
}