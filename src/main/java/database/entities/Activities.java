/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import database.entities.StampRallys;
import database.entities.Stamps;
import database.entities.Users;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author b3314
 */
@Entity
@Table(name = "activities")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activities.findAll", query = "SELECT a FROM Activities a")
    , @NamedQuery(name = "Activities.findByActivityId", query = "SELECT a FROM Activities a WHERE a.activityId = :activityId")
    , @NamedQuery(name = "Activities.findByActivityFlag", query = "SELECT a FROM Activities a WHERE a.activityFlag = :activityFlag")})
public class Activities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "activity_id")
    private Integer activityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "activity_flag")
    private String activityFlag;
    @JoinTable(name = "activity_goods", joinColumns = {
        @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToMany
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityId")
    private Collection<Users> usersCollection1;
    @JoinColumn(name = "stamp_id", referencedColumnName = "stamp_id")
    @ManyToOne(optional = false)
    private Stamps stampId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;
    @JoinColumn(name = "stamprally_id", referencedColumnName = "stamprally_id")
    @ManyToOne(optional = false)
    private StampRallys stamprallyId;

    public Activities() {
    }

    public Activities(Integer activityId) {
        this.activityId = activityId;
    }

    public Activities(Integer activityId, String activityFlag) {
        this.activityId = activityId;
        this.activityFlag = activityFlag;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityFlag() {
        return activityFlag;
    }

    public void setActivityFlag(String activityFlag) {
        this.activityFlag = activityFlag;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection1() {
        return usersCollection1;
    }

    public void setUsersCollection1(Collection<Users> usersCollection1) {
        this.usersCollection1 = usersCollection1;
    }

    public Stamps getStampId() {
        return stampId;
    }

    public void setStampId(Stamps stampId) {
        this.stampId = stampId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public StampRallys getStamprallyId() {
        return stamprallyId;
    }

    public void setStamprallyId(StampRallys stamprallyId) {
        this.stamprallyId = stamprallyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activityId != null ? activityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activities)) {
            return false;
        }
        Activities other = (Activities) object;
        if ((this.activityId == null && other.activityId != null) || (this.activityId != null && !this.activityId.equals(other.activityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Activities[ activityId=" + activityId + " ]";
    }
    
}
