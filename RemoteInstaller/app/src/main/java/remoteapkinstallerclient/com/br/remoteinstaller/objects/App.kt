package remoteapkinstallerclient.com.br.remoteinstaller.objects

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.Serializable

/**
 * Created by joao.roriz on 04/12/17.
 */

class App : Serializable {

    var appName: String? = null
    var packageName: String? = null
    var versionName: String? = null
    var versionCode: Int? = null
    var addedDate: Long? = null
    var checksum: String? = null
    var changelog: String? = null
    var icon: String? = null


    fun getBitmapIcon() :Bitmap{
        val decodedString = Base64.decode(icon, Base64.URL_SAFE)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        return decodedByte;
    }
}
