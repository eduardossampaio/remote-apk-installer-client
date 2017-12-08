package remoteapkinstallerclient.com.br.remoteinstaller.service.http

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.net.MalformedURLException
import java.net.URL

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import remoteapkinstallerclient.com.br.remoteinstaller.service.Constants
import remoteapkinstallerclient.com.br.remoteinstaller.service.preferences.Preferences
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    private var retrofit: Retrofit? = null

    private fun getUrl(context: Context): String? {
        try {

            val hostName = Preferences.readPreferenceValue(context, Preferences.PreferencesEntry.HOST_NAME)
            val portNum = Preferences.readPreferenceValue(context, Preferences.PreferencesEntry.PORT)
            val url = URL("http", hostName, Integer.parseInt(portNum), "apk/")
            return url.toString()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        return null
    }

    fun getClient(context: Context): Retrofit? {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        retrofit = Retrofit.Builder()
                .baseUrl(getUrl(context)!!)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()


        return retrofit
    }
}