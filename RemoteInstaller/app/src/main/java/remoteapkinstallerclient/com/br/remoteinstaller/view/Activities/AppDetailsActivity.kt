package remoteapkinstallerclient.com.br.remoteinstaller.view.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_app_details.*
import remoteapkinstallerclient.com.br.remoteinstaller.R
import remoteapkinstallerclient.com.br.remoteinstaller.objects.App
import remoteapkinstallerclient.com.br.remoteinstaller.utils.DateUtils


class AppDetailsActivity : AppCompatActivity() {
    companion object {
        public val EXTRA_APP = "EXTRA_APP"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_details)
        val app = intent.extras.getSerializable(EXTRA_APP) as App
        tv_nome.text = app.appName
        tv_app_package.text = app.packageName
        tv_app_version.text = app.versionName
        tv_app_version_code.text = "${app.versionCode}"
        tv_hour.text = DateUtils.toString("dd/MM/yyy - HH:mm",app.addedDate!!)


    }
}
