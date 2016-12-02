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
public class FriendRequestsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "request_id")
    private int requestId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "permit_id")
    private int permitId;

    public FriendRequestsPK() {
    }

    public FriendRequestsPK(int requestId, int permitId) {
        this.requestId = requestId;
        this.permitId = permitId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getPermitId() {
        return permitId;
    }

    public void setPermitId(int permitId) {
        this.permitId = permitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) requestId;
        hash += (int) permitId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendRequestsPK)) {
            return false;
        }
        FriendRequestsPK other = (FriendRequestsPK) object;
        if (this.requestId != other.requestId) {
            return false;
        }
        if (this.permitId != other.permitId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FriendRequestsPK[ requestId=" + requestId + ", permitId=" + permitId + " ]";
    }
    
}
