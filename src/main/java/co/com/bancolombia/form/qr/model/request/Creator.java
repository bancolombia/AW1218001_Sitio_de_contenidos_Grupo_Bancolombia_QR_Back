package co.com.bancolombia.form.qr.model.request;

import io.swagger.annotations.ApiModelProperty;

public class Creator {

    @ApiModelProperty(value = "channel", example = "50")
    private String channel;

    @ApiModelProperty(value = "adviser Id", example = "55122")
    private String adviserId;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(String adviserId) {
        this.adviserId = adviserId;
    }

    @Override
    public String toString() {
        return "Creator{" +
                "channel='" + channel + '\'' +
                ", adviserId='" + adviserId + '\'' +
                '}';
    }
}
