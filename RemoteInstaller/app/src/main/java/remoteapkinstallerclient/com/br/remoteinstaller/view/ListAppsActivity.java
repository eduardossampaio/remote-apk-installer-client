package remoteapkinstallerclient.com.br.remoteinstaller.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import remoteapkinstallerclient.com.br.remoteinstaller.R;
import remoteapkinstallerclient.com.br.remoteinstaller.objects.App;
import remoteapkinstallerclient.com.br.remoteinstaller.objects.AppsResponse;
import remoteapkinstallerclient.com.br.remoteinstaller.service.APIClient;
import remoteapkinstallerclient.com.br.remoteinstaller.service.AppService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListAppsActivity extends AppCompatActivity {

    private AppsAdapter appsAdapter;
    private RecyclerView rvApps;

    private ProgressDialog progressDialog;
    private List<App> listApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_apps);

        rvApps = findViewById(R.id.rv_apps);

        Retrofit retrofit = APIClient.getClient();

        AppService resquestManager = retrofit.create(AppService.class);
        progressDialog = ProgressDialog.show(this, "Remote Installer", "Carregando...", true);
        resquestManager.getApps().enqueue(new Callback<AppsResponse>() {
            @Override
            public void onResponse(Call<AppsResponse> call, Response<AppsResponse> response) {
                if(response.isSuccessful()) {
                    progressDialog.dismiss();
                    listApps = response.body().getResponse();
                    initList(listApps);
                }else{
                    Toast.makeText(ListAppsActivity.this, "Erro 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AppsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListAppsActivity.this, "Erro 2", Toast.LENGTH_SHORT).show();

            }
        });
    }


    void initList(List<App> apps){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        appsAdapter = new AppsAdapter(apps, this);
        rvApps.setLayoutManager(linearLayoutManager);
        rvApps.setAdapter(appsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_refresh){
            refresh();
        }else if(item.getItemId()==R.id.menu_settings){
            goToSettings();
        }else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void refresh(){
        Toast.makeText(this, "refreshing", Toast.LENGTH_SHORT).show();
    }
    private void goToSettings(){
        startActivity(new Intent(this,SettingsActivity.class));
    }
}
