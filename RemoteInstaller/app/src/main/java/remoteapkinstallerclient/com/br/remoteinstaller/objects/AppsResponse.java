package remoteapkinstallerclient.com.br.remoteinstaller.objects;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.roriz on 04/12/17.
 */

public class AppsResponse implements Serializable{
    private ResponseHeader header;
    private List<App> response;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public List<App> getResponse() {
        return response;
    }

    public void setResponse(List<App> response) {
        this.response = response;
    }
}
