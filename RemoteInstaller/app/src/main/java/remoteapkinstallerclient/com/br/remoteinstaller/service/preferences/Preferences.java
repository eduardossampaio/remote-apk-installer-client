package remoteapkinstallerclient.com.br.remoteinstaller.service.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by eduardo.sampaio on 07/12/17.
 */

public class Preferences {

    public  enum PreferencesEntry {

        HOST_NAME("preferences_ip", "localhost"),
        PORT("preferences_port", "8080");

        private String defaultValue;
        private String preferenceKey;

        PreferencesEntry( String preferenceKey,String defaultValue) {
            this.defaultValue = defaultValue;
            this.preferenceKey = preferenceKey;
        }

    }


    public static String readPreferenceValue(Context context,PreferencesEntry entry){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  sharedPreferences.getString(entry.preferenceKey,entry.defaultValue);
    }

}
