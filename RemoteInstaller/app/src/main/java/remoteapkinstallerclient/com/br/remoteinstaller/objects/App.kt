package remoteapkinstallerclient.com.br.remoteinstaller.objects

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
    var changelog: List<String>? = null
}
