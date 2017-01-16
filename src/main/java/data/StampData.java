package data;

public class StampData {
    private Integer stampId;
    private String stampName;
    private String stampDate;
    private String stampRallyName;
    private Integer stampRallyReviewAveragePoint;
    private byte[] picture;

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Integer getStampId() {
        return stampId;
    }

    public void setStampId(Integer stampId) {
        this.stampId = stampId;
    }

    public String getStampName() {
        return stampName;
    }

    public void setStampName(String stampName) {
        this.stampName = stampName;
    }

    public String getStampDate() {
        return stampDate;
    }

    public void setStampDate(String stampDate) {
        this.stampDate = stampDate;
    }

    public String getStampRallyName() {
        return stampRallyName;
    }

    public void setStampRallyName(String stampRallyName) {
        this.stampRallyName = stampRallyName;
    }

    public Integer getStampRallyReviewAveragePoint() {
        return stampRallyReviewAveragePoint;
    }
    public void setStampRallyReviewAveragePoint(Integer stampRallyReviewAveragePoint) {
        this.stampRallyReviewAveragePoint = stampRallyReviewAveragePoint;
    }
}
