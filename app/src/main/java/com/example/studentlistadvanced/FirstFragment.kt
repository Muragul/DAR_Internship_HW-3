package com.example.studentlistadvanced

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstFragment : Fragment(), Adapter.ItemClickListener, Adapter.RemoveItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = Adapter(this)
        recyclerView.adapter = adapter
        adapter.submitList(StudentList.getStudentList())

        rootView.findViewById<Button>(R.id.add_student).setOnClickListener {
            val name = rootView.findViewById<EditText>(R.id.student_name_to_add).text.toString()
            if (name != "") {
                addStudentToList(name)
                rootView.findViewById<EditText>(R.id.student_name_to_add).text.clear()
            }
        }

        return rootView
    }

    private fun addStudentToList(name: String) {
        StudentList.addStudent(Student(name))
        adapter.submitList(StudentList.getStudentList())
    }

    override fun itemClicked(item: Student) {
        view?.findNavController()?.navigate(FirstFragmentDirections.actionToDetails(item))
    }

    override fun itemRemoved(item: Student) {
        StudentList.removeStudent(item)
        adapter.submitList(StudentList.getStudentList())
    }

}