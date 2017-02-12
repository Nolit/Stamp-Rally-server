package database.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "stamp_rallys")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StampRallys.findAll", query = "SELECT s FROM StampRallys s")
    , @NamedQuery(name = "StampRallys.findByStamprallyId", query = "SELECT s FROM StampRallys s WHERE s.stamprallyId = :stamprallyId")
    , @NamedQuery(name = "StampRallys.findBySearchId", query = "SELECT s FROM StampRallys s WHERE s.searchId = :searchId")
    , @NamedQuery(name = "StampRallys.findByStamprallyName", query = "SELECT s FROM StampRallys s WHERE s.stamprallyName = :stamprallyName")
    , @NamedQuery(name = "StampRallys.findBySearchKeyWord", query = "SELECT s FROM StampRallys s WHERE s.stamprallyName LIKE :keyword OR s.stamrallyComment LIKE :keyword")})

public class StampRallys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stamprally_id")
    private Integer stamprallyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "stamp_thumbnail")
    private String stampThumbnail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "search_id")
    private String searchId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stamprally_name")
    private String stamprallyName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stamrally_comment")
    private String stamrallyComment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "official_flag")
    private boolean officialFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @ManyToMany(mappedBy = "stampRallysCollection")
    private Collection<Tags> tagsCollection = new ArrayList<>();
    @JoinTable(name = "stamps_rally_structures", joinColumns = {
        @JoinColumn(name = "stamprally_id", referencedColumnName = "stamprally_id")}, inverseJoinColumns = {
        @JoinColumn(name = "stamp_id", referencedColumnName = "stamp_id")})
    @ManyToMany
    private List<Stamps> stampsList = new ArrayList<>();
    @JoinTable(name = "stamps_rally_creaters", joinColumns = {
        @JoinColumn(name = "stamprally_id", referencedColumnName = "stamprally_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToMany
    private List<Users> usersCollection = new ArrayList<>();
    @ManyToMany(mappedBy = "stampRallysList")
    private Collection<Stamps> stampsCollection1 = new ArrayList<>();

    @OneToMany(mappedBy = "stamprally")
    private Collection<StampBookLikes> stampBookLikesCollection = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stampRallys")
    private Collection<RallyCompleteUsers> rallyCompleteUsersCollection = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stamprallyId")
    private Collection<Reports> reportsCollection = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stamprallyId")
    private Collection<Questions> questionsCollection = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stamprallyId")
    private Collection<Users> usersCollection1 = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stampRallys")
    private Collection<Reviews> reviewsCollection = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stamprallyId")
    private Collection<Activities> activitiesCollection = new ArrayList<>();

    public StampRallys() {
        officialFlag = false;
        createDate = new Date();
    }

    public StampRallys(Integer stamprallyId) {
        this.stamprallyId = stamprallyId;
    }

    public StampRallys(Integer stamprallyId, String stampThumbnail, String searchId, String stamprallyName, String stamrallyComment, boolean officialFlag, Date createDate) {
        this.stamprallyId = stamprallyId;
        this.stampThumbnail = stampThumbnail;
        this.searchId = searchId;
        this.stamprallyName = stamprallyName;
        this.stamrallyComment = stamrallyComment;
        this.officialFlag = officialFlag;
        this.createDate = createDate;
    }

    public Integer getStamprallyId() {
        return stamprallyId;
    }

    public void setStamprallyId(Integer stamprallyId) {
        this.stamprallyId = stamprallyId;
    }

    public String getStampThumbnail() {
        return stampThumbnail;
    }

    public void setStampThumbnail(String stampThumbnail) {
        this.stampThumbnail = stampThumbnail;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getStamprallyName() {
        return stamprallyName;
    }

    public void setStamprallyName(String stamprallyName) {
        this.stamprallyName = stamprallyName;
    }

    public String getStamrallyComment() {
        return stamrallyComment;
    }

    public void setStamrallyComment(String stamrallyComment) {
        this.stamrallyComment = stamrallyComment;
    }

    public boolean getOfficialFlag() {
        return officialFlag;
    }

    public void setOfficialFlag(boolean officialFlag) {
        this.officialFlag = officialFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @XmlTransient
    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
    }

    @XmlTransient
    public List<StampPads> getStructurePads() {
        List<StampPads> stampPadsList = new ArrayList<>();
        for(Stamps stamp : stampsList){
            stampPadsList.add(stamp.getStampPads());
        }
        return stampPadsList;
    }
    
    public void addStamp(Stamps stamp){
        stampsList.add(stamp);
    }
    
    public void setStampList(List<Stamps> stampList){
        this.stampsList = stampList;
    }
    
    public List<Stamps> getStampList(){
        return stampsList;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersCollection;
    }

    public void setUsersList(List<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<Stamps> getStampsCollection1() {
        return stampsCollection1;
    }

    public void setStampsCollection1(Collection<Stamps> stampsCollection1) {
        this.stampsCollection1 = stampsCollection1;
    }

    @XmlTransient
    public Collection<StampBookLikes> getStampBookLikesCollection() {
        return stampBookLikesCollection;
    }

    public void setStampBookLikesCollection(Collection<StampBookLikes> stampBookLikesCollection) {
        this.stampBookLikesCollection = stampBookLikesCollection;
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
    public Collection<Questions> getQuestionsCollection() {
        return questionsCollection;
    }

    public void setQuestionsCollection(Collection<Questions> questionsCollection) {
        this.questionsCollection = questionsCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection1() {
        return usersCollection1;
    }

    public void setUsersCollection1(Collection<Users> usersCollection1) {
        this.usersCollection1 = usersCollection1;
    }

    @XmlTransient
    public Collection<Reviews> getReviewsCollection() {
        return reviewsCollection;
    }

    public void setReviewsCollection(Collection<Reviews> reviewsCollection) {
        this.reviewsCollection = reviewsCollection;
    }

    @XmlTransient
    public Collection<Activities> getActivitiesCollection() {
        return activitiesCollection;
    }

    public void setActivitiesCollection(Collection<Activities> activitiesCollection) {
        this.activitiesCollection = activitiesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stamprallyId != null ? stamprallyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StampRallys)) {
            return false;
        }
        StampRallys other = (StampRallys) object;
        if ((this.stamprallyId == null && other.stamprallyId != null) || (this.stamprallyId != null && !this.stamprallyId.equals(other.stamprallyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StampRallys[ stamprallyId=" + stamprallyId + " ]";
    }
    
}
