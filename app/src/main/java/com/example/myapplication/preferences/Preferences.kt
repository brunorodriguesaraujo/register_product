package com.example.myapplication.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences

const val APP_PREFS = "app_prefs"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(APP_PREFS)