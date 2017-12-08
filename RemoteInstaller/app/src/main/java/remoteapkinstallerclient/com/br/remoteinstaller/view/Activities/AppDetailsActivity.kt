package remoteapkinstallerclient.com.br.remoteinstaller.view.Activities

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_app_details.*
import remoteapkinstallerclient.com.br.remoteinstaller.R
import remoteapkinstallerclient.com.br.remoteinstaller.objects.App
import remoteapkinstallerclient.com.br.remoteinstaller.service.download.DownloadService
import remoteapkinstallerclient.com.br.remoteinstaller.utils.DateUtils
import ru.noties.markwon.Markwon
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.support.v4.app.ActivityCompat
import android.os.Build
import android.support.v4.app.FragmentActivity


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

        app_icon.setImageBitmap(app.getBitmapIcon());
        tv_hour.text = DateUtils.toString("dd/MM/yyy - HH:mm", app.addedDate!!)
        Markwon.setMarkdown(changelog_text, app.changelog!!);

        btn_install.setOnClickListener(View.OnClickListener {
            if(isStoragePermissionGranted()) {
                DownloadService.download(this, app);
            }
        })

    }

    fun isStoragePermissionGranted(): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("", "Permission is granted")
                return true
            } else {

                Log.v("", "Permission is revoked")
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                return false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("", "Permission is granted")
            return true
        }
    }
}
