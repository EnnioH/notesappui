package com.example.notesappui.data.local

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("notes_app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_ONBOARDING_SHOWN = "is_onboarding_shown"
    }

    fun setIsOnboardingShown(isShown: Boolean) {
        sharedPreferences.edit()
            .putBoolean(KEY_IS_ONBOARDING_SHOWN, isShown)
            .apply()
    }

    fun isOnboardingShown(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_ONBOARDING_SHOWN, false)
    }
}