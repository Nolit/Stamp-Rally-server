/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

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
@Table(name = "friend_requests")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FriendRequests.findAll", query = "SELECT f FROM FriendRequests f")
    , @NamedQuery(name = "FriendRequests.findByRequestId", query = "SELECT f FROM FriendRequests f WHERE f.friendRequestsPK.requestId = :requestId")
    , @NamedQuery(name = "FriendRequests.findByPermitId", query = "SELECT f FROM FriendRequests f WHERE f.friendRequestsPK.permitId = :permitId")
    , @NamedQuery(name = "FriendRequests.findByRequestDate", query = "SELECT f FROM FriendRequests f WHERE f.requestDate = :requestDate")})
public class FriendRequests implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FriendRequestsPK friendRequestsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "request_date")
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    @JoinColumn(name = "permit_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "request_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users1;

    public FriendRequests() {
    }

    public FriendRequests(FriendRequestsPK friendRequestsPK) {
        this.friendRequestsPK = friendRequestsPK;
    }

    public FriendRequests(FriendRequestsPK friendRequestsPK, Date requestDate) {
        this.friendRequestsPK = friendRequestsPK;
        this.requestDate = requestDate;
    }

    public FriendRequests(int requestId, int permitId) {
        this.friendRequestsPK = new FriendRequestsPK(requestId, permitId);
    }

    public FriendRequestsPK getFriendRequestsPK() {
        return friendRequestsPK;
    }

    public void setFriendRequestsPK(FriendRequestsPK friendRequestsPK) {
        this.friendRequestsPK = friendRequestsPK;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Users getUsers1() {
        return users1;
    }

    public void setUsers1(Users users1) {
        this.users1 = users1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friendRequestsPK != null ? friendRequestsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendRequests)) {
            return false;
        }
        FriendRequests other = (FriendRequests) object;
        if ((this.friendRequestsPK == null && other.friendRequestsPK != null) || (this.friendRequestsPK != null && !this.friendRequestsPK.equals(other.friendRequestsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FriendRequests[ friendRequestsPK=" + friendRequestsPK + " ]";
    }
    
}
