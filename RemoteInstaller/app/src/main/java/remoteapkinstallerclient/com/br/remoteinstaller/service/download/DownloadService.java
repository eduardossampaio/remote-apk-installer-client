package remoteapkinstallerclient.com.br.remoteinstaller.service.download;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import remoteapkinstallerclient.com.br.remoteinstaller.objects.App;
import remoteapkinstallerclient.com.br.remoteinstaller.service.Constants;

/**
 * Created by eduardo.sampaio on 06/12/17.
 */

public class DownloadService {

    String DOWNLOAD_URL = Constants.DOWNLOAD_APP_BASE_URL;

    public void download(Context context, App app) throws UnsupportedEncodingException {
        String url = DOWNLOAD_URL+ URLEncoder.encode(app.getChecksum(), "UTF-8");
        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("Baixando apk do aplicativo"+app.getName());
        request.setMimeType("application/vnd.android.package-archive");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        String fileName = app.getChecksum()+".apk";
        String path="apks";
        request.setDestinationInExternalPublicDir(path, fileName);
        dm.enqueue(request);
    }

}
