package remoteapkinstallerclient.com.br.remoteinstaller.service;

import android.content.Context;

import java.util.List;

import remoteapkinstallerclient.com.br.remoteinstaller.objects.App;
import remoteapkinstallerclient.com.br.remoteinstaller.service.http.APIClient;
import remoteapkinstallerclient.com.br.remoteinstaller.service.http.apps.AppHttpService;
import remoteapkinstallerclient.com.br.remoteinstaller.service.http.apps.AppsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by eduardo.sampaio on 07/12/17.
 */

public class AppService {

    public interface ListAppsServiceCallback{

        void onSuccess(List<App> apps);

        void onError(Throwable cause);
    }

    public void listApplications(Context context, final ListAppsServiceCallback callback){
        Retrofit retrofit = APIClient.getClient(context);
        AppHttpService resquestManager = retrofit.create(AppHttpService.class);

        resquestManager.getApps().enqueue(new Callback<AppsResponse>() {
            @Override
            public void onResponse(Call<AppsResponse> call, Response<AppsResponse> response) {
                if(response.isSuccessful()) {
                    List<App> listApps = response.body().getResponse();
                    callback.onSuccess(listApps);
                }else{
                    callback.onError(new RuntimeException("Error processing request"));
                }
            }

            @Override
            public void onFailure(Call<AppsResponse> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
}
