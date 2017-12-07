package remoteapkinstallerclient.com.br.remoteinstaller.view.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import remoteapkinstallerclient.com.br.remoteinstaller.R;
import remoteapkinstallerclient.com.br.remoteinstaller.objects.App;
import remoteapkinstallerclient.com.br.remoteinstaller.service.AppService;
import remoteapkinstallerclient.com.br.remoteinstaller.view.adapter.ListApksAdapter;

public class ListAppsActivity extends AppCompatActivity {

    private ListApksAdapter appsAdapter;
    private RecyclerView rvApps;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_apps);
        rvApps = findViewById(R.id.rv_apps);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestApps();
    }

    private void requestApps(){
        progressDialog = ProgressDialog.show(this, "Remote Installer", "Carregando...", true);
        AppService appService = new AppService();
        appService.listApplications(this, new AppService.ListAppsServiceCallback() {
            @Override
            public void onSuccess(List<App> apps) {
                progressDialog.dismiss();
                initList(apps);
            }

            @Override
            public void onError(Throwable cause) {
                progressDialog.dismiss();
                Toast.makeText(ListAppsActivity.this, "Error: "+cause.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    void initList(List<App> apps){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        appsAdapter = new AppsAdapter(apps, this);
        appsAdapter = new ListApksAdapter(apps, this);
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
        requestApps();
    }
    private void goToSettings(){
        startActivity(new Intent(this,SettingsActivity.class));
    }
}
