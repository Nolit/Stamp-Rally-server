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
@Table(name = "reviews")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reviews.averageByStamrallyId", query = "SELECT avg(r.review) FROM Reviews r WHERE r.reviewsPK.stampRallyId = :stampRallyId")
    , @NamedQuery(name = "Reviews.findEvaluatedData", query = "SELECT r FROM Reviews r WHERE r.reviewsPK.stampRallyId = :stampRallyId AND r.reviewsPK.userId = :userId")})
public class Reviews implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ReviewsPK reviewsPK;
    
    @NotNull
    @Column(name = "review")
    private int review;
    
    @NotNull
    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    
    @JoinColumn(name = "stamprally_id", referencedColumnName = "stamprally_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StampRallys stampRallys;

    public Reviews() {
    }

    public Reviews(ReviewsPK reviewsPK) {
        this.reviewsPK = reviewsPK;
    }

    public Reviews(ReviewsPK reviewsPK, int review, Date updateDate) {
        this.reviewsPK = reviewsPK;
        this.review = review;
        this.updateDate = updateDate;
    }

    public Reviews(int stamrallyId, int userId) {
        this.reviewsPK = new ReviewsPK(stamrallyId, userId);
    }

    public ReviewsPK getReviewsPK() {
        return reviewsPK;
    }

    public void setReviewsPK(ReviewsPK reviewsPK) {
        this.reviewsPK = reviewsPK;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public StampRallys getStampRallys() {
        return stampRallys;
    }

    public void setStampRallys(StampRallys stampRallys) {
        this.stampRallys = stampRallys;
    }

    @Override
    public String toString() {
        return "entities.Reviews[ reviewsPK=" + reviewsPK + " ]";
    }
    
}
