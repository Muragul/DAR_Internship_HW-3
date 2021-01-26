package com.example.studentlistadvanced

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Student(
    val id: Int,
    val name: String,
    val surname: String?,
    val grade: Double?,
    val image: String?
) : Parcelable {
    constructor(name: String) : this(StudentList.getNewId(), name, "", 0.0, "")

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Student) return false
        val user: Student = other
//        return user.id == id && user.name == name && user.surname == surname &&
//                user.grade == grade && user.image == image
        return user.name == name
    }

    override fun hashCode(): Int {
//        return Objects.hash(id, name, surname)
        return Objects.hash(name)
    }
}