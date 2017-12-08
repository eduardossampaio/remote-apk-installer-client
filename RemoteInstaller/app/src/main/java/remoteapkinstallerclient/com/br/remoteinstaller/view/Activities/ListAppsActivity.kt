package remoteapkinstallerclient.com.br.remoteinstaller.view.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

import remoteapkinstallerclient.com.br.remoteinstaller.R
import remoteapkinstallerclient.com.br.remoteinstaller.objects.App
import remoteapkinstallerclient.com.br.remoteinstaller.service.AppService
import remoteapkinstallerclient.com.br.remoteinstaller.view.adapter.ListApksAdapter

class ListAppsActivity : AppCompatActivity() {

    private var appsAdapter: ListApksAdapter? = null
    private var rvApps: RecyclerView? = null

    private var progressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_apps)
        rvApps = findViewById(R.id.rv_apps)
    }

    override fun onResume() {
        super.onResume()
        requestApps()
    }

    private fun requestApps() {
        progressDialog = ProgressDialog.show(this, "Remote Installer", "Carregando...", true)
        val appService = AppService()
        appService.listApplications(this, object : AppService.ListAppsServiceCallback {
            override fun onSuccess(apps: List<App>?) {
                progressDialog!!.dismiss()
                if(apps!=null) {
                    initList(apps)
                }else{

                }
            }

            override fun onError(cause: Throwable) {
                progressDialog!!.dismiss()
                showError(cause.localizedMessage);
            }
        })
    }


    internal fun initList(apps: List<App>) {
        val linearLayoutManager = LinearLayoutManager(this)
        appsAdapter = ListApksAdapter(apps, this)
        rvApps!!.layoutManager = linearLayoutManager
        rvApps!!.adapter = appsAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            refresh()
        } else if (item.itemId == R.id.menu_settings) {
            goToSettings()
        } else {
            return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun refresh() {
        requestApps()
    }

    private fun goToSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun showError(msg: String){
        Toast.makeText(this@ListAppsActivity, msg, Toast.LENGTH_SHORT).show()
    }
}
