package remoteapkinstallerclient.com.br.remoteinstaller.view.Activities;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import remoteapkinstallerclient.com.br.remoteinstaller.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class MyPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            updateSummaryForPreference("preferences_ip");
            updateSummaryForPreference("preferences_port");
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            String value = o.toString();
            if (preference instanceof EditTextPreference) {
                EditTextPreference etp = (EditTextPreference) preference;
                preference.setSummary(etp.getText());
            }
            return true;
        }

        private void updateSummaryForPreference(String preferenceKey) {
            findPreference(preferenceKey).setOnPreferenceChangeListener(this);
        }

    }

}
