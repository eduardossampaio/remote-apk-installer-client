package remoteapkinstallerclient.com.br.remoteinstaller.service;

import java.util.List;

import remoteapkinstallerclient.com.br.remoteinstaller.objects.AppsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by joao.roriz on 04/12/17.
 */

public interface AppService {

//    @POST("v2/5a25bc532e00008334a906fa")
    @Headers("Content-Type: application/json")
    @POST("listAll")
    Call<AppsResponse> getApps();
}
