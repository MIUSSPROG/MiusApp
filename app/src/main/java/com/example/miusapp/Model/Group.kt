package com.example.miusapp.Model

class Group internal constructor(
    val id: String = "",
    val name: String = "",
    val ageFrom: Long = 0,
    val ageTo: Long = 0,
    val dateStart: String = "",
    val dateEnd: String = "",
    val form: String = "",
    var studentsCount: Long = 0
//    val students: MutableList<Student> = ArrayList()
)