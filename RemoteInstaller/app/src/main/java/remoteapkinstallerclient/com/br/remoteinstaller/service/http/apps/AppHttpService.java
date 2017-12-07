package remoteapkinstallerclient.com.br.remoteinstaller.service.http.apps;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by joao.roriz on 04/12/17.
 */

public interface AppHttpService {

    @Headers("Content-Type: application/json")
    @POST("listAll")
    Call<AppsResponse> getApps();
}
