package com.example.studentlistadvanced

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FirstFragment : Fragment(), Adapter.ItemClickListener, Adapter.RemoveItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var studentStack = CustomStack()
    private var stateBundle: Bundle? = null

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

        rootView.findViewById<Button>(R.id.restore_button).setOnClickListener {
            if (!studentStack.empty()) {
                StudentList.restoreStudent(studentStack.pop())
                adapter.submitList(StudentList.getStudentList())
            }
        }

        rootView.findViewById<Button>(R.id.add_student).setOnClickListener {
            val name = rootView.findViewById<EditText>(R.id.student_name_to_add).text.toString()
            if (name != "") {
                addStudentToList(name)
                rootView.findViewById<EditText>(R.id.student_name_to_add).text.clear()
            } else
                Toast.makeText(context, "Fill out the data", Toast.LENGTH_SHORT).show()
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
        studentStack.push(item)
        StudentList.removeStudent(item)
        adapter.submitList(StudentList.getStudentList())
    }

    override fun onPause() {
        super.onPause()
        stateBundle = Bundle()
        val listState: Parcelable? = recyclerView.layoutManager?.onSaveInstanceState()
        stateBundle!!.putParcelable("recycler_state", listState)
    }

    override fun onResume() {
        super.onResume()
        if (stateBundle != null) {
            val listState: Parcelable? = stateBundle!!.getParcelable("recycler_state")
            recyclerView.layoutManager?.onRestoreInstanceState(listState)
        }
    }

}