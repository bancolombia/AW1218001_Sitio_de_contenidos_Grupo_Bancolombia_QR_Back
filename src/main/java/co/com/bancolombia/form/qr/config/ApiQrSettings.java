package co.com.bancolombia.form.qr.config;

import co.com.bancolombia.form.qr.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "api.aws.qr")
@Component
public class ApiQrSettings {

    private String host;
    private String path;
    private String resourceQrCodes;
    private String hostRenew;
    private String pathRenew;
    private String operation;
    private String resourceRequestShipping;
    private String channel;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getResourceQrCodes() {
        return resourceQrCodes;
    }

    public void setResourceQrCodes(String resourceQrCodes) {
        this.resourceQrCodes = resourceQrCodes;
    }

    public String getResourceRequestShipping() {
        return resourceRequestShipping;
    }

    public void setResourceRequestShipping(String resourceRequestShipping) {
        this.resourceRequestShipping = resourceRequestShipping;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getHostRenew() {
        return hostRenew;
    }

    public void setHostRenew(String hostRenew) {
        this.hostRenew = hostRenew;
    }

    public String getPathRenew() {
        return pathRenew;
    }

    public void setPathRenew(String pathRenew) {
        this.pathRenew = pathRenew;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getFullPathQrCodes() {
        return String.format(Constant.APIQR_CONCAT_FULL_PATH, getHost(), getPath(), getResourceQrCodes());
    }

    public String getFullPathRequestShipping() {
        return String.format(Constant.APIQR_CONCAT_FULL_PATH, getHost(), getPath(), getResourceRequestShipping());
    }

    public String getFullPathRequestRenew() {
        return String.format(Constant.APIQR_CONCAT_FULL_PATH, getHostRenew(), getPathRenew(), getOperation());
    }

    @Override
    public String toString() {
        return String.format("CaptchaSettings [host=%s, path=%s, resourceQrCodes=%s, resourceRequestShipping=%s, channel=%s, fullPathQrCodes=%s, fullPathRequestShipping=%s,hostRenew=%s, pathRenew=%s, operation=%s, FullPathRequestRenew]", getHost(), getPath(), getResourceQrCodes(), getResourceRequestShipping(), getChannel(), getFullPathQrCodes(), getFullPathRequestShipping(), getHostRenew(), getPathRenew(), getOperation(), getFullPathRequestRenew());
    }

}