package com.example.notesappui.data.local

import android.content.Context
import android.content.SharedPreferences

class PrefManager(private val context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("notes_app", Context.MODE_PRIVATE)

    fun setOnBoardShown() {
        pref.edit().putBoolean("onboard_shown", true).apply()
    }

    fun isOnBoardShown(): Boolean {
        return pref.getBoolean("onboard_shown", false)
    }
}