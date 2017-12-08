package remoteapkinstallerclient.com.br.remoteinstaller.service.http.apps

import java.io.Serializable

import remoteapkinstallerclient.com.br.remoteinstaller.objects.App

/**
 * Created by joao.roriz on 04/12/17.
 */

class AppsResponse : Serializable {
    var header: ResponseHeader? = null
    var response: List<App>? = null
}
