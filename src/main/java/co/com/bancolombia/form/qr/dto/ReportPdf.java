package co.com.bancolombia.form.qr.dto;

import org.springframework.stereotype.Component;

import co.com.bancolombia.form.qr.Constant;
@Component
public class ReportPdf {

    private String title;
    private byte[] qrImage;
    private float qrImagefixedPositionLeft;
    private float qrImagefixedPositionBottom;
    private float scaleToFitWidth;
    private float scaleToFitHeight;
    private byte[] backGroundImage;
    private byte[] backGroundImageTwo;
    private int page;

    public String getTitle() {
        return title;
    }

    public byte[] getQrImage() {
        return qrImage;
    }

    public float getQrImagefixedPositionLeft() {
        return qrImagefixedPositionLeft;
    }

    public float getQrImagefixedPositionBottom() {
        return qrImagefixedPositionBottom;
    }

    public float getScaleToFitWidth() {
        return scaleToFitWidth;
    }

    public float getScaleToFitHeight() {
        return scaleToFitHeight;
    }

    public byte[] getBackGroundImage() {
        return backGroundImage;
    }

    public int getPage() {
        return page;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQrImage(byte[] qrImage) {
        this.qrImage = qrImage;
    }

    public void setQrImagefixedPositionLeft(float qrImagefixedPositionLeft) {
        this.qrImagefixedPositionLeft = qrImagefixedPositionLeft;
    }

    public void setQrImagefixedPositionBottom(float qrImagefixedPositionBottom) {
        this.qrImagefixedPositionBottom = qrImagefixedPositionBottom;
    }

    public void setScaleToFitWidth(float scaleToFitWidth) {
        this.scaleToFitWidth = scaleToFitWidth;
    }

    public void setScaleToFitHeight(float scaleToFitHeight) {
        this.scaleToFitHeight = scaleToFitHeight;
    }

    public void setBackGroundImage(byte[] backGroundImage) {
        this.backGroundImage = backGroundImage;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public byte[] getBackGroundImageTwo() {
        return backGroundImageTwo;
    }

    public void setBackGroundImageTwo(byte[] backGroundImageTwo) {
        this.backGroundImageTwo = backGroundImageTwo;
    }

    @Override
    public String toString() {
        return String.format(
                "ReportPdf [title=%s, qrImage=%s, qrImagefixedPositionLeft=%s, qrImagefixedPositionBottom=%s," +
                        " scaleToFitWidth=%s, scaleToFitHeight=%s, backGroundImage=%s, backGroundImageTwo=%s, page=%s]",
                getTitle(),
                ((getQrImage() == null || getQrImage().length == 0) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR
                        : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY),
                getQrImagefixedPositionLeft(), getQrImagefixedPositionBottom(), getScaleToFitWidth(),
                getScaleToFitHeight(),
                ((getBackGroundImage() == null || getBackGroundImage().length == 0)
                        ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR
                        : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY),
                ((getBackGroundImageTwo() == null || getBackGroundImageTwo().length == 0)
                        ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR
                        : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY),
                getPage());
    }

}
