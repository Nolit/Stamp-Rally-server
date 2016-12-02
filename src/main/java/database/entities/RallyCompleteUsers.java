/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import database.entities.StampRallys;
import database.entities.Users;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b3314
 */
@Entity
@Table(name = "rally_complete_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RallyCompleteUsers.findAll", query = "SELECT r FROM RallyCompleteUsers r")
    , @NamedQuery(name = "RallyCompleteUsers.findByAchieverId", query = "SELECT r FROM RallyCompleteUsers r WHERE r.rallyCompleteUsersPK.achieverId = :achieverId")
    , @NamedQuery(name = "RallyCompleteUsers.findByStamprallyId", query = "SELECT r FROM RallyCompleteUsers r WHERE r.rallyCompleteUsersPK.stamprallyId = :stamprallyId")
    , @NamedQuery(name = "RallyCompleteUsers.findByChallangeDate", query = "SELECT r FROM RallyCompleteUsers r WHERE r.challangeDate = :challangeDate")
    , @NamedQuery(name = "RallyCompleteUsers.findByAchieveDate", query = "SELECT r FROM RallyCompleteUsers r WHERE r.achieveDate = :achieveDate")})
public class RallyCompleteUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RallyCompleteUsersPK rallyCompleteUsersPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "challange_date")
    @Temporal(TemporalType.DATE)
    private Date challangeDate;
    @Column(name = "achieve_date")
    @Temporal(TemporalType.DATE)
    private Date achieveDate;
    @JoinColumn(name = "stamprally_id", referencedColumnName = "stamprally_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StampRallys stampRallys;
    @JoinColumn(name = "achiever_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public RallyCompleteUsers() {
    }

    public RallyCompleteUsers(RallyCompleteUsersPK rallyCompleteUsersPK) {
        this.rallyCompleteUsersPK = rallyCompleteUsersPK;
    }

    public RallyCompleteUsers(RallyCompleteUsersPK rallyCompleteUsersPK, Date challangeDate) {
        this.rallyCompleteUsersPK = rallyCompleteUsersPK;
        this.challangeDate = challangeDate;
    }

    public RallyCompleteUsers(int achieverId, int stamprallyId) {
        this.rallyCompleteUsersPK = new RallyCompleteUsersPK(achieverId, stamprallyId);
    }

    public RallyCompleteUsersPK getRallyCompleteUsersPK() {
        return rallyCompleteUsersPK;
    }

    public void setRallyCompleteUsersPK(RallyCompleteUsersPK rallyCompleteUsersPK) {
        this.rallyCompleteUsersPK = rallyCompleteUsersPK;
    }

    public Date getChallangeDate() {
        return challangeDate;
    }

    public void setChallangeDate(Date challangeDate) {
        this.challangeDate = challangeDate;
    }

    public Date getAchieveDate() {
        return achieveDate;
    }

    public void setAchieveDate(Date achieveDate) {
        this.achieveDate = achieveDate;
    }

    public StampRallys getStampRallys() {
        return stampRallys;
    }

    public void setStampRallys(StampRallys stampRallys) {
        this.stampRallys = stampRallys;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rallyCompleteUsersPK != null ? rallyCompleteUsersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RallyCompleteUsers)) {
            return false;
        }
        RallyCompleteUsers other = (RallyCompleteUsers) object;
        if ((this.rallyCompleteUsersPK == null && other.rallyCompleteUsersPK != null) || (this.rallyCompleteUsersPK != null && !this.rallyCompleteUsersPK.equals(other.rallyCompleteUsersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RallyCompleteUsers[ rallyCompleteUsersPK=" + rallyCompleteUsersPK + " ]";
    }
    
}
