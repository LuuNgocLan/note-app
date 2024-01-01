package lanltn.com.note_app.features.login

import android.app.Application
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lanltn.com.note_app.data.source.prefs.SharedPrefKey.Companion.PREFS_LOGGED_IN_KEY
import lanltn.com.note_app.data.source.prefs.SharedPrefKey.Companion.PREFS_PASSWORD_KEY
import lanltn.com.note_app.data.source.prefs.SharedPrefKey.Companion.PREFS_USERNAME_KEY

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = PreferenceManager.getDefaultSharedPreferences(application)

    private val _username = MutableLiveData<String?>(null)
    private val _password = MutableLiveData<String?>(null)
    private val _isLoggedIn = MutableLiveData<Boolean>(false)

    internal val username: LiveData<String?> get() = _username
    internal val password: LiveData<String?> get() = _password
    internal val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn


    private val listener: SharedPreferences.OnSharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            when (key) {
                PREFS_USERNAME_KEY -> _username.value = sharedPreferences.getString(key, null)
                PREFS_PASSWORD_KEY -> _password.value = sharedPreferences.getString(key, null)
                PREFS_LOGGED_IN_KEY -> _isLoggedIn.value = sharedPreferences.getBoolean(key, false)
                else -> Unit
            }
        }

    init {
        _isLoggedIn.value = prefs.getBoolean(PREFS_LOGGED_IN_KEY, false)
        _username.value = prefs.getString(PREFS_USERNAME_KEY, null)
        _password.value = prefs.getString(PREFS_PASSWORD_KEY, null)
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    fun onLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            prefs.edit(commit = true) {
                putString(PREFS_USERNAME_KEY, username)
                putString(PREFS_PASSWORD_KEY, password)
                putBoolean(PREFS_LOGGED_IN_KEY, true)
            }
        }
        _isLoggedIn.value = true
    }

    override fun onCleared() {
        prefs.unregisterOnSharedPreferenceChangeListener(listener)
        super.onCleared()
    }
}