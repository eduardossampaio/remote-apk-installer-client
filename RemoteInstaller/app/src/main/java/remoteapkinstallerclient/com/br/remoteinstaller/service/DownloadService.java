package remoteapkinstallerclient.com.br.remoteinstaller.service;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.webkit.DownloadListener;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import remoteapkinstallerclient.com.br.remoteinstaller.objects.App;

/**
 * Created by eduardo.sampaio on 06/12/17.
 */

public class DownloadService {

    String DOWNLOAD_URL = Urls.DOWNLOAD_APP_BASE_URL;

    public void download(Context context, App app) throws UnsupportedEncodingException {
        String url = DOWNLOAD_URL+ URLEncoder.encode(app.getChecksum(), "UTF-8");
        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("Baixando apk do aplicativo"+app.getName());
//        request.setDescription("Android Data download using DownloadManager.");
        request.setMimeType("application/vnd.android.package-archive");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        String fileName = app.getChecksum()+".apk";
        String path="apks";
        request.setDestinationInExternalPublicDir(path, fileName);
        dm.enqueue(request);
    }

}
