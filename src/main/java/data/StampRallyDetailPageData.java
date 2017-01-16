package data;

public class StampRallyDetailPageData {


    //スタンプラリー情報
    private Integer stampRallyId;
    private String stampRallyTitle;
    private String stampRallyComment;
    private Integer stampRallyReviewPoint;
    private Integer stampRallyReviewAveragePoint;
    
    //ユーザー情報
    private Integer stampRallyCreatorUserId;
    private String stampRallyCreatorsUserName;
    private String referenceUserId;
    private String referenceUserName;
    
    //ボタン情報
    private String stampRallyChallengeDate;
    private String stampRallyCompleteDate;
    
    
    public Integer getStampRallyId() {
        return stampRallyId;
    }
    public void setStampRallyId(Integer stampRallyId) {
        this.stampRallyId = stampRallyId;
    }
    public String getStampRallyTitle() {
        return stampRallyTitle;
    }
    public void setStampRallyTitle(String stampRallyTitle) {
        this.stampRallyTitle = stampRallyTitle;
    }
    public String getStampRallyComment() {
        return stampRallyComment;
    }
    public void setStampRallyComment(String stampRallyComment) {
        this.stampRallyComment = stampRallyComment;
    }
    public Integer getStampRallyReviewPoint() {
        return stampRallyReviewPoint;
    }
    public void setStampRallyReviewPoint(Integer stampRallyReviewPoint) {
        this.stampRallyReviewPoint = stampRallyReviewPoint;
    }
    public Integer getStampRallyCreatorUserId() {
        return stampRallyCreatorUserId;
    }
    public void setStampRallyCreatorUserId(Integer stampRallyCreatorUserId) {
        this.stampRallyCreatorUserId = stampRallyCreatorUserId;
    }
    public String getStampRallyCreatorsUserName() {
        return stampRallyCreatorsUserName;
    }
    public void setStampRallyCreatorsUserName(String stampRallyCreatorsUserName) {
        this.stampRallyCreatorsUserName = stampRallyCreatorsUserName;
    }
    public String getReferenceUserId() {
        return referenceUserId;
    }
    public void setReferenceUserId(String referenceUserId) {
        this.referenceUserId = referenceUserId;
    }
    public String getReferenceUserName() {
        return referenceUserName;
    }
    public void setReferenceUserName(String referenceUserName) {
        this.referenceUserName = referenceUserName;
    }
    public String getStampRallyChallengeDate() {
        return stampRallyChallengeDate;
    }
    public void setStampRallyChallengeDate(String stampRallyChallengeDate) {
        this.stampRallyChallengeDate = stampRallyChallengeDate;
    }
    public String getStampRallyCompleteDate() {
        return stampRallyCompleteDate;
    }
    public void setStampRallyCompleteDate(String stampRallyCompleteDate) {
        this.stampRallyCompleteDate = stampRallyCompleteDate;
    }
    public Integer getStampRallyReviewAveragePoint() {
        return stampRallyReviewAveragePoint;
    }
    public void setStampRallyReviewAveragePoint(Integer stampRallyReviewAveragePoint) {
        this.stampRallyReviewAveragePoint = stampRallyReviewAveragePoint;
    }
}
