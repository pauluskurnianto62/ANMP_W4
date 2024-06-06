package com.paulus.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.paulus.studentapp.databinding.StudentListItemBinding
import com.paulus.studentapp.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(),
    ButtonDetailClickListener {
    class StudentViewHolder(var binding:StudentListItemBinding ):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.student = studentList[position]
        holder.binding.listener = this
        //holder.binding.txtId.text = studentList[position].id
        //holder.binding.txtName.text = studentList[position].name
        //holder.binding.btnDetail.setOnClickListener {
            //val action = StudentListFragmentDirections.actionStudentDetailFragment(studentList[position].id.toString())
            //Navigation.findNavController(it).navigate(action)
        //}
        //val picasso = Picasso.Builder(holder.itemView.context)
        //picasso.listener { picasso, uri, exception ->
            //exception.printStackTrace()
        //}
        //picasso.build().load(studentList[position].photoUrl).into(holder.binding.imgStudent, object: Callback {
            //override fun onSuccess() {
                //holder.binding.progressImage.visibility = View.INVISIBLE
                //holder.binding.imgStudent.visibility = View.VISIBLE
            //}

            //override fun onError(e: Exception?) {
                //Log.e("picasso_error", e.toString())
            //}
        //})
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetailFragment(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}