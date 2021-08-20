package com.example.miusapp.Utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Prefs(context: Context) {

    companion object{
        private const val PREFS_FILENAME = "myPrefs"
        private const val KEY_UUID = "myUUID"
        private const val KEY_DESC = "myDesc"
        private const val KEY_TEACHER_ID = "teacherID"
    }

    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var myUUId: String
        get() = sharedPref.getString(KEY_UUID, "") ?: ""
        set(value) = sharedPref.edit{ putString(KEY_UUID, value)}

    var myDesc: String
        get() = sharedPref.getString(KEY_DESC, "") ?: ""
        set(value) = sharedPref.edit{ putString(KEY_DESC, value)}

    var teacherID: String
        get() = sharedPref.getString(KEY_TEACHER_ID, "") ?: ""
        set(value) = sharedPref.edit { putString(KEY_TEACHER_ID, value) }

}