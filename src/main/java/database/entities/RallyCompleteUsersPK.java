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
public class RallyCompleteUsersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "achiever_id")
    private int achieverId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stamprally_id")
    private int stamprallyId;

    public RallyCompleteUsersPK() {
    }

    public RallyCompleteUsersPK(int achieverId, int stamprallyId) {
        this.achieverId = achieverId;
        this.stamprallyId = stamprallyId;
    }

    public int getAchieverId() {
        return achieverId;
    }

    public void setAchieverId(int achieverId) {
        this.achieverId = achieverId;
    }

    public int getStamprallyId() {
        return stamprallyId;
    }

    public void setStamprallyId(int stamprallyId) {
        this.stamprallyId = stamprallyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) achieverId;
        hash += (int) stamprallyId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RallyCompleteUsersPK)) {
            return false;
        }
        RallyCompleteUsersPK other = (RallyCompleteUsersPK) object;
        if (this.achieverId != other.achieverId) {
            return false;
        }
        if (this.stamprallyId != other.stamprallyId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RallyCompleteUsersPK[ achieverId=" + achieverId + ", stamprallyId=" + stamprallyId + " ]";
    }
    
}
