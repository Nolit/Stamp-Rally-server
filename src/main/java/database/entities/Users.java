package database.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId")
    , @NamedQuery(name = "Users.findBySearchId", query = "SELECT u FROM Users u WHERE u.searchId = :searchId")
    , @NamedQuery(name = "Users.findByMailAddress", query = "SELECT u FROM Users u WHERE u.mailAddress = :mailAddress")
    , @NamedQuery(name = "Users.findBySex", query = "SELECT u FROM Users u WHERE u.sex = :sex")
    , @NamedQuery(name = "Users.findByBirthday", query = "SELECT u FROM Users u WHERE u.birthday = :birthday")
    , @NamedQuery(name = "Users.findByThumbnail", query = "SELECT u FROM Users u WHERE u.thumbnail = :thumbnail")
    , @NamedQuery(name = "Users.findByProfile", query = "SELECT u FROM Users u WHERE u.profile = :profile")
    , @NamedQuery(name = "Users.findByAdmissionDay", query = "SELECT u FROM Users u WHERE u.admissionDay = :admissionDay")
    , @NamedQuery(name = "Users.findByPrivateFlag", query = "SELECT u FROM Users u WHERE u.privateFlag = :privateFlag")
    , @NamedQuery(name = "Users.findByMailAddressAndPassword", query = "SELECT u FROM Users u WHERE u.mailAddress = :mailAddress AND u.password = :password")
    , @NamedQuery(name = "Users.findByDeleteDate", query = "SELECT u FROM Users u WHERE u.deleteDate = :deleteDate")
    , @NamedQuery(name = "Users.findByLoginData", query = "SELECT  u FROM Users u WHERE u.mailAddress = :mailAddress AND u.password = :password")
})

public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "search_id", unique = true)
    private String searchId;
    
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "mail_address", unique = true)
    private String mailAddress;
    
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String password;
    
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "user_name")
    private String userName;
    
    @Basic(optional = true)
    private boolean sex;
    
    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @Basic(optional = true)
    @Size(min = 1, max = 9)
    private String thumbnail;
    
    @Basic(optional = true)
    @Size(min = 1, max = 9)
    private String profile;
    
    @Basic(optional = true)
    @Column(name = "admission_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date admissionDay;
    
    @NotNull
    @Column(name = "private_flag")
    private boolean privateFlag;
    
    @Column(name = "delete_date")
    @Temporal(TemporalType.DATE)
    private Date deleteDate;
    
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Admins> adminsCollection;
    
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Activities> activitiesCollection;
    
    @ManyToMany(mappedBy = "usersCollection")
    private List<StampRallys> stampRallysCollection;
    
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Questions> questionsCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<StampBookLikes> stampBookLikes;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<RallyCompleteUsers> rallyCompleteUsersCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportedUserId")
    private Collection<Reports> reportsCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Reports> reportsCollection1;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<FriendRequests> friendRequestsCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users1")
    private Collection<FriendRequests> friendRequestsCollection1;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Questions> questionsCollection1;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Friends> friendsCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users1")
    private Collection<Friends> friendsCollection1;
    
    @JoinColumn(name = "stamprally_id", referencedColumnName = "stamprally_id")
    @ManyToOne(optional = false)
    private StampRallys stamprallyId;
    
    @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
    @ManyToOne(optional = false)
    private Activities activityId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Reviews> reviewsCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Activities> activitiesCollection1;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<StampPads> myStampCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Stamps> clearStampCollection;
    
    @Transient
    private byte[] thumbnailData;
    @Transient
    private static boolean MALE = true;
    @Transient
    private static boolean FEMALE = false;
    @Transient
    public int followUserCount;
    @Transient
    public int followerCount;
    @Transient
    public boolean isFollow;

    public Users(){
        
    }
    public Users(String email, String password, String userName) {
        this.mailAddress = email;
        this.password = password;
        this.userName = userName;
        this.searchId = UUID.randomUUID().toString().substring(0, 14);
        this.admissionDay = new Date();
    }

    public Users(Integer userId, String searchId, String mailAddress, String password, String userName, boolean sex, Date birthday, String thumbnail, String profile, Date admissionDay, boolean privateFlag) {
        this.userId = userId;
        this.searchId = searchId;
        this.mailAddress = mailAddress;
        this.password = password;
        this.userName = userName;
        this.sex = sex;
        this.birthday = birthday;
        this.thumbnail = thumbnail;
        this.profile = profile;
        this.admissionDay = admissionDay;
        this.privateFlag = privateFlag;
    }

    public void setUserId(int id){
        this.userId = id;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonIgnore
    public boolean isMale() {
        return sex == MALE;
    }
    
    @JsonIgnore
    public boolean isFemale() {
        return sex == FEMALE;
    }

    public void setMale() {
        this.sex = MALE;
    }
    
    public void setFemale() {
        this.sex = FEMALE;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Date getAdmissionDay() {
        return admissionDay;
    }

    public void setAdmissionDay(Date admissionDay) {
        this.admissionDay = admissionDay;
    }

    @JsonIgnore
    public boolean isPrivateAccount() {
        return privateFlag == true;
    }

    public void setPrivateAccount() {
        this.privateFlag = true;
    }
    
    public void setPublicAccount() {
        this.privateFlag = false;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    @XmlTransient
    public Collection<Activities> getActivitiesCollection() {
        return activitiesCollection;
    }

    public void addActivity(Activities myActivity) {
        activitiesCollection.add(myActivity);
    }

    @XmlTransient
    public List<StampRallys> getStampRallysCollection() {
        return stampRallysCollection;
    }

    public void addCreatedStampRally(StampRallys myStampRally) {
        stampRallysCollection.add(myStampRally);
    }

    public List<StampBookLikes> getStampBookLikes() {
        return stampBookLikes;
    }

    public void likedStampBook(StampBookLikes stampBookLike) {
        stampBookLikes.add(stampBookLike);
    }

    @XmlTransient
    public Collection<FriendRequests> getFriendRequestsCollection() {
        return friendRequestsCollection;
    }

    public void setFriendRequestsCollection(Collection<FriendRequests> friendRequestsCollection) {
        this.friendRequestsCollection = friendRequestsCollection;
    }

    @XmlTransient
    public Collection<FriendRequests> getFriendRequestsCollection1() {
        return friendRequestsCollection1;
    }

    public void setFriendRequestsCollection1(Collection<FriendRequests> friendRequestsCollection1) {
        this.friendRequestsCollection1 = friendRequestsCollection1;
    }

    @XmlTransient
    public Collection<Questions> getQuestionsCollection1() {
        return questionsCollection1;
    }

    public void setQuestionsCollection1(Collection<Questions> questionsCollection1) {
        this.questionsCollection1 = questionsCollection1;
    }

    @XmlTransient
    public Collection<Friends> getFriendsCollection() {
        return friendsCollection;
    }

    public void setFriendsCollection(Collection<Friends> friendsCollection) {
        this.friendsCollection = friendsCollection;
    }

    @XmlTransient
    public Collection<Friends> getFriendsCollection1() {
        return friendsCollection1;
    }

    public void setFriendsCollection1(Collection<Friends> friendsCollection1) {
        this.friendsCollection1 = friendsCollection1;
    }

    public StampRallys getStamprallyId() {
        return stamprallyId;
    }

    public void setStamprallyId(StampRallys stamprallyId) {
        this.stamprallyId = stamprallyId;
    }

    public Activities getActivityId() {
        return activityId;
    }

    public void setActivityId(Activities activityId) {
        this.activityId = activityId;
    }

    @XmlTransient
    public Collection<Reviews> getReviewsCollection() {
        return reviewsCollection;
    }

    public void setReviewsCollection(Collection<Reviews> reviewsCollection) {
        this.reviewsCollection = reviewsCollection;
    }

    @XmlTransient
    public Collection<Activities> getActivitiesCollection1() {
        return activitiesCollection1;
    }

    public void setActivitiesCollection1(Collection<Activities> activitiesCollection1) {
        this.activitiesCollection1 = activitiesCollection1;
    }

    @XmlTransient
    public Collection<StampPads> getStampPadsCollection() {
        return myStampCollection;
    }

    public void setStampPadsCollection(Collection<StampPads> stampPadsCollection) {
        this.myStampCollection = stampPadsCollection;
    }

    @XmlTransient
    public List<Stamps> getStampsCollection() {
        return clearStampCollection;
    }

    public void setStampsCollection(List<Stamps> stampsCollection) {
        this.clearStampCollection = stampsCollection;
    }
    
    public byte[] getThumbnailData() {
        return thumbnailData;
    }

    public void setThumbnailData(byte[] thumbnailData) {
        this.thumbnailData = thumbnailData;
    }

    @Override
    public String toString() {
        return "entities.Users[ userId=" + userId + " ]";
    }
    
}
