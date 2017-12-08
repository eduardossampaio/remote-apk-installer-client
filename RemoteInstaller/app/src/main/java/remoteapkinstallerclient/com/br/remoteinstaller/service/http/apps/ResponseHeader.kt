package remoteapkinstallerclient.com.br.remoteinstaller.service.http.apps

import java.io.Serializable

/**
 * Created by joao.roriz on 04/12/17.
 */

class ResponseHeader : Serializable {
    var status: Int = 0
    var errorMessage: String? = null
}
