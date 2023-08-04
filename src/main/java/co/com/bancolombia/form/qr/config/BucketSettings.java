package co.com.bancolombia.form.qr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BucketSettings {
    @Value("${aws.host:}")
    private String endPoint;
    @Value("${aws.s3.bucketName:}")
    private String bucketName;
    @Value("${aws.s3.cockadeSvgKeyOne:}")
    private String cockadeSvgKeyOne;
    @Value("${aws.s3.cockadeSvgKeyTwo:}")
    private String cockadeSvgKeyTwo;
    @Value("${aws.s3.talkativeSvgKey:}")
    private String talkativeSvgKey;

    public String getEndPoint() {
        return endPoint;
    }

    public String getBucketName() {
        if (bucketName != null) {
            return bucketName.trim();
        } else {
            return null;
        }
    }

    public String getCockadeSvgKey() {
        if (cockadeSvgKeyOne != null) {
            return cockadeSvgKeyOne.trim();
        } else {
            return null;
        }
    }

    public String getCockadeSvgKeyTwo() {
        if (cockadeSvgKeyTwo != null) {
            return cockadeSvgKeyTwo.trim();
        } else {
            return null;
        }
    }

    public String getTalkativeSvgKey() {
        if (talkativeSvgKey != null) {
            return talkativeSvgKey.trim();
        } else {
            return null;
        }
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void setCockadeSvgKey(String cockadeSvgKeyOne) {
        this.cockadeSvgKeyOne = cockadeSvgKeyOne;
    }

    public void setCockadeSvgKeyTwo(String cockadeSvgKeyTwo) {
        this.cockadeSvgKeyTwo = cockadeSvgKeyTwo;
    }

    public void setTalkativeSvgKey(String talkativeSvgKey) {
        this.talkativeSvgKey = talkativeSvgKey;
    }

    @Override
    public String toString() {
        return String.format("BucketSettings [endPoint=%s, bucketName=%s, cockadeSvgKeyOne=%s, cockadeSvgKeyTwo=%s," +
                        " talkativeSvgKey=%s]", getEndPoint(), getBucketName(), getCockadeSvgKey(), getCockadeSvgKeyTwo(),
                getTalkativeSvgKey());
    }

}