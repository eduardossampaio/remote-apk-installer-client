package remoteapkinstallerclient.com.br.remoteinstaller.service.download

import android.app.DownloadManager
import android.content.Context
import android.net.Uri

import java.io.UnsupportedEncodingException
import java.net.URLEncoder

import remoteapkinstallerclient.com.br.remoteinstaller.objects.App
import remoteapkinstallerclient.com.br.remoteinstaller.service.Constants
import java.io.File

/**
 * Created by eduardo.sampaio on 06/12/17.
 */

class DownloadService {
    companion object {
        internal var DOWNLOAD_URL = Constants.DOWNLOAD_APP_BASE_URL

        @Throws(UnsupportedEncodingException::class)
        fun download(context: Context, app: App) {
            val url = DOWNLOAD_URL + URLEncoder.encode(app.checksum, "UTF-8")
            val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val request = DownloadManager.Request(Uri.parse(url))
            request.setTitle("Baixando apk do aplicativo" + app.appName!!)
            request.setMimeType("application/vnd.android.package-archive")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setVisibleInDownloadsUi(false)
            val fileName = app.checksum!! + ".apk"
            val path = "apks"

            val file:File =File("/storage/emulated/0/apks")

            if(!file.exists()){
                file.mkdir();
            }
            request.setDestinationInExternalPublicDir(path, fileName)
            dm.enqueue(request)
        }
    }
}
