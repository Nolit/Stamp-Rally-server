/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import database.entities.Reviews;
import database.entities.Questions;
import database.entities.Admins;
import database.entities.RallyCompleteUsers;
import database.entities.Reports;
import database.entities.Friends;
import database.entities.FriendRequests;
import database.entities.Activities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author b3314
 */
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
    , @NamedQuery(name = "Users.findByDeleteDate", query = "SELECT u FROM Users u WHERE u.deleteDate = :deleteDate")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "search_id")
    private String searchId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "mail_address")
    private String mailAddress;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sex")
    private boolean sex;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "thumbnail")
    private String thumbnail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "profile")
    private String profile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "admission_day")
    @Temporal(TemporalType.DATE)
    private Date admissionDay;
    @Basic(optional = false)
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
    private Collection<StampRallys> stampRallysCollection;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Questions> questionsCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
    private StampBookLikes stampBookLikes;
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

    public Users() {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
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

    public boolean getPrivateFlag() {
        return privateFlag;
    }

    public void setPrivateFlag(boolean privateFlag) {
        this.privateFlag = privateFlag;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    @XmlTransient
    public Collection<Admins> getAdminsCollection() {
        return adminsCollection;
    }

    public void setAdminsCollection(Collection<Admins> adminsCollection) {
        this.adminsCollection = adminsCollection;
    }

    @XmlTransient
    public Collection<Activities> getActivitiesCollection() {
        return activitiesCollection;
    }

    public void setActivitiesCollection(Collection<Activities> activitiesCollection) {
        this.activitiesCollection = activitiesCollection;
    }

    @XmlTransient
    public Collection<StampRallys> getStampRallysCollection() {
        return stampRallysCollection;
    }

    public void setStampRallysCollection(Collection<StampRallys> stampRallysCollection) {
        this.stampRallysCollection = stampRallysCollection;
    }

    @XmlTransient
    public Collection<Questions> getQuestionsCollection() {
        return questionsCollection;
    }

    public void setQuestionsCollection(Collection<Questions> questionsCollection) {
        this.questionsCollection = questionsCollection;
    }

    public StampBookLikes getStampBookLikes() {
        return stampBookLikes;
    }

    public void setStampBookLikes(StampBookLikes stampBookLikes) {
        this.stampBookLikes = stampBookLikes;
    }

    @XmlTransient
    public Collection<RallyCompleteUsers> getRallyCompleteUsersCollection() {
        return rallyCompleteUsersCollection;
    }

    public void setRallyCompleteUsersCollection(Collection<RallyCompleteUsers> rallyCompleteUsersCollection) {
        this.rallyCompleteUsersCollection = rallyCompleteUsersCollection;
    }

    @XmlTransient
    public Collection<Reports> getReportsCollection() {
        return reportsCollection;
    }

    public void setReportsCollection(Collection<Reports> reportsCollection) {
        this.reportsCollection = reportsCollection;
    }

    @XmlTransient
    public Collection<Reports> getReportsCollection1() {
        return reportsCollection1;
    }

    public void setReportsCollection1(Collection<Reports> reportsCollection1) {
        this.reportsCollection1 = reportsCollection1;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Users[ userId=" + userId + " ]";
    }
    
}
