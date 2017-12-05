package remoteapkinstallerclient.com.br.remoteinstaller.objects;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.roriz on 04/12/17.
 */

public class App implements Serializable {

    private String appName;
    private String packageName;
    private String versionName;
    private Integer versionCode;
    private Long addedDate;
    private String checksum;
    private List<String> changelog;

    public String getName() {
        return appName;
    }

    public List<String> getChangelog() {
        return changelog;
    }

    public void setChangelog(List<String> changelog) {
        this.changelog = changelog;
    }

    public void setName(String name) {
        this.appName = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public Long getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Long addedDate) {
        this.addedDate = addedDate;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
