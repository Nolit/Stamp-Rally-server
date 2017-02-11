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
public class ReviewsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "stamprally_id")
    private int stampRallyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;

    public ReviewsPK() {
    }

    public ReviewsPK(int stamrallyId, int userId) {
        this.stampRallyId = stamrallyId;
        this.userId = userId;
    }

    public int getStamrallyId() {
        return stampRallyId;
    }

    public void setStamrallyId(int stamrallyId) {
        this.stampRallyId = stamrallyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) stampRallyId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewsPK)) {
            return false;
        }
        ReviewsPK other = (ReviewsPK) object;
        if (this.stampRallyId != other.stampRallyId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ReviewsPK[ stamrallyId=" + stampRallyId + ", userId=" + userId + " ]";
    }
    
}
