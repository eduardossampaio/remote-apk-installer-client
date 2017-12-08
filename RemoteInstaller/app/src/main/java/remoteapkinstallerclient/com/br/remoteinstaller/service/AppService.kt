package remoteapkinstallerclient.com.br.remoteinstaller.service

import android.content.Context

import remoteapkinstallerclient.com.br.remoteinstaller.objects.App
import remoteapkinstallerclient.com.br.remoteinstaller.service.http.APIClient
import remoteapkinstallerclient.com.br.remoteinstaller.service.http.apps.AppHttpService
import remoteapkinstallerclient.com.br.remoteinstaller.service.http.apps.AppsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by eduardo.sampaio on 07/12/17.
 */

class AppService {

    public interface ListAppsServiceCallback {

        fun onSuccess(apps: List<App>?)

        fun onError(cause: Throwable)
    }

    fun listApplications(context: Context, callback: ListAppsServiceCallback) {
        val retrofit = APIClient.getClient(context)
        val resquestManager = retrofit?.create(AppHttpService::class.java)

        resquestManager?.apps?.enqueue(object : Callback<AppsResponse> {
            override fun onResponse(call: Call<AppsResponse>, response: Response<AppsResponse>) {
                if (response.isSuccessful) {
                    val listApps = response.body()!!.response
                    callback.onSuccess(listApps)
                } else {
                    callback.onError(RuntimeException("Error processing request"))
                }
            }

            override fun onFailure(call: Call<AppsResponse>, throwable: Throwable) {
                callback.onError(throwable)
            }
        })
    }
}
