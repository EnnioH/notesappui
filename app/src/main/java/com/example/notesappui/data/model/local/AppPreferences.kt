package com.example.notesapp.data.local

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun setOnBoardCompleted() {
        sharedPreferences.edit()
            .putBoolean(KEY_ONBOARD_COMPLETED, true)
            .apply()
    }

    fun isOnBoardCompleted(): Boolean {
        return sharedPreferences.getBoolean(KEY_ONBOARD_COMPLETED, false)
    }

    companion object {
        private const val KEY_ONBOARD_COMPLETED = "onboard_completed"
    }
}