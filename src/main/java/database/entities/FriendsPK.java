/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author b3314
 */
@Embeddable
public class FriendsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "follow_id")
    private int followId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "follower_id")
    private int followerId;

    public FriendsPK() {
    }

    public FriendsPK(int followId, int followerId) {
        this.followId = followId;
        this.followerId = followerId;
    }

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) followId;
        hash += (int) followerId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendsPK)) {
            return false;
        }
        FriendsPK other = (FriendsPK) object;
        if (this.followId != other.followId) {
            return false;
        }
        if (this.followerId != other.followerId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FriendsPK[ followId=" + followId + ", followerId=" + followerId + " ]";
    }
    
}
