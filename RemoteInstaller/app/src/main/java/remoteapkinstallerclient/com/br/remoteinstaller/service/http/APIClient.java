package remoteapkinstallerclient.com.br.remoteinstaller.service.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import remoteapkinstallerclient.com.br.remoteinstaller.service.Constants;
import remoteapkinstallerclient.com.br.remoteinstaller.service.preferences.Preferences;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;

    private static String getUrl(Context context){
        try {

            String hostName= Preferences.readPreferenceValue(context, Preferences.PreferencesEntry.HOST_NAME);
            String portNum = Preferences.readPreferenceValue(context, Preferences.PreferencesEntry.PORT);
            URL url = new URL("http",hostName,Integer.parseInt(portNum),"apk/");
            return url.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Retrofit getClient(Context context) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl(getUrl(context))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();


        return retrofit;
    }
}