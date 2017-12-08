package remoteapkinstallerclient.com.br.remoteinstaller.service.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by eduardo.sampaio on 07/12/17.
 */

object Preferences {

    enum class PreferencesEntry private constructor(val preferenceKey: String, val defaultValue: String) {
        HOST_NAME("preferences_ip", "localhost"),
        PORT("preferences_port", "8080")

    }

    fun readPreferenceValue(context: Context, entry: PreferencesEntry): String? {
        return readPreferenceValue(context,entry.preferenceKey,entry.defaultValue)
    }
    fun readPreferenceValue(context: Context, preferenceKey: String,defaultValue: String): String? {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString(preferenceKey, defaultValue)
    }

}
