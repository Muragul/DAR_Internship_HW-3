package com.example.studentlistadvanced

object StudentList {
    private var studentList: MutableSet<Student> = mutableSetOf()
    private var counter: Int = 1

    fun getStudentList(): List<Student> {
        return studentList.toList()
    }

    fun addStudent(student: Student) {
        if (!studentList.contains(student))
            counter++
        studentList.add(student)
    }

    fun restoreStudent(student: Student){
        studentList.add(student)
    }

    fun removeStudent(student: Student) {
        studentList.remove(student)
    }

    fun getStudentListSize(): Int {
        return studentList.size
    }

    fun getNewId(): Int {
        return counter
    }

}