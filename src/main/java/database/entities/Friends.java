/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@Table(name = "friends")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friends.findAll", query = "SELECT f FROM Friends f")
    , @NamedQuery(name = "Friends.findByFollowId", query = "SELECT f FROM Friends f WHERE f.friendsPK.followId = :followId")
    , @NamedQuery(name = "Friends.findByFollowSet", query = "SELECT f FROM Friends f WHERE f.friendsPK.followId = :followId AND f.friendsPK.followerId = :followerId")
    , @NamedQuery(name = "Friends.findByFollowerId", query = "SELECT f FROM Friends f WHERE f.friendsPK.followerId = :followerId")//Delete from Member m where m.name like :name
    , @NamedQuery(name = "Friends.deleteByUserSet", query = "Delete FROM Friends f WHERE f.friendsPK.followId = :followId AND f.friendsPK.followerId = :followerId")
    , @NamedQuery(name = "Friends.findByFollowDate", query = "SELECT f FROM Friends f WHERE f.followDate = :followDate")})
public class Friends implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FriendsPK friendsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "follow_date")
    @Temporal(TemporalType.DATE)
    private Date followDate;
    @JoinColumn(name = "follower_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "follow_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users1;

    public Friends() {
    }

    public Friends(FriendsPK friendsPK) {
        this.friendsPK = friendsPK;
    }

    public Friends(FriendsPK friendsPK, Date followDate) {
        this.friendsPK = friendsPK;
        this.followDate = followDate;
    }

    public Friends(int followId, int followerId) {
        this.friendsPK = new FriendsPK(followId, followerId);
    }
    
    public Friends(Users followUser, Users follower) {
        this.friendsPK = new FriendsPK(followUser.getUserId(), follower.getUserId());
        this.users = followUser;
        this.users1 = follower;
        this.followDate = new Date();
    } 

    public FriendsPK getFriendsPK() {
        return friendsPK;
    }

    public void setFriendsPK(FriendsPK friendsPK) {
        this.friendsPK = friendsPK;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
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
        hash += (friendsPK != null ? friendsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friends)) {
            return false;
        }
        Friends other = (Friends) object;
        if ((this.friendsPK == null && other.friendsPK != null) || (this.friendsPK != null && !this.friendsPK.equals(other.friendsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Friends[ friendsPK=" + friendsPK + " ]";
    }
    
}
