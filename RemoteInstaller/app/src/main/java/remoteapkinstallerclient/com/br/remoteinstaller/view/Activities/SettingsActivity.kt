package remoteapkinstallerclient.com.br.remoteinstaller.view.Activities

import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

import remoteapkinstallerclient.com.br.remoteinstaller.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager.beginTransaction().replace(android.R.id.content, MyPreferenceFragment()).commit()

        if (supportActionBar != null) {
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    class MyPreferenceFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.preferences)
            updateSummaryForPreference("preferences_ip")
            updateSummaryForPreference("preferences_port")
        }

        override fun onPreferenceChange(preference: Preference, o: Any): Boolean {
            val value = o.toString()
            (preference as? EditTextPreference)?.setSummary(preference.text)
            return true
        }

        private fun updateSummaryForPreference(preferenceKey: String) {
            findPreference(preferenceKey).onPreferenceChangeListener = this
        }

    }

}
