package remoteapkinstallerclient.com.br.remoteinstaller.service.http.apps;

import java.io.Serializable;

/**
 * Created by joao.roriz on 04/12/17.
 */

public class ResponseHeader implements Serializable {
    private int status;
    private String errorMessage;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
