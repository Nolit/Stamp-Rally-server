package data;

public class StampRallyData {
    private Integer stampRallyId;
    private String stampRallyTitle;
    private String stampRallyCreatorName;
    private byte[] picture;

    /**
     * @return the stampRallyId
     */
    public Integer getStampRallyId() {
        return stampRallyId;
    }

    /**
     * @param stampRallyId the stampRallyId to set
     */
    public void setStampRallyId(Integer stampRallyId) {
        this.stampRallyId = stampRallyId;
    }

    /**
     * @return the stampRallyTitle
     */
    public String getStampRallyTitle() {
        return stampRallyTitle;
    }

    /**
     * @param stampRallyTitle the stampRallyTitle to set
     */
    public void setStampRallyTitle(String stampRallyTitle) {
        this.stampRallyTitle = stampRallyTitle;
    }

    /**
     * @return the stampRallyCreatorName
     */
    public String getStampRallyCreatorName() {
        return stampRallyCreatorName;
    }

    /**
     * @param stampRallyCreatorName the stampRallyCreatorName to set
     */
    public void setStampRallyCreatorName(String stampRallyCreatorName) {
        this.stampRallyCreatorName = stampRallyCreatorName;
    }

    /**
     * @return the picture
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
