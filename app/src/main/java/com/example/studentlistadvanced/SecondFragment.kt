package com.example.studentlistadvanced

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs

class SecondFragment : Fragment() {
    private val arguments: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_second, container, false)
        val student = arguments.student
        rootView.findViewById<TextView>(R.id.student_id).text = student.id.toString()
        rootView.findViewById<TextView>(R.id.student_name).text = student.name
        rootView.findViewById<TextView>(R.id.student_surname).text = student.surname
        rootView.findViewById<TextView>(R.id.student_grade).text = student.grade.toString()

        return rootView
    }

}