/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "stamp_book_likes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StampBookLikes.findAll", query = "SELECT s FROM StampBookLikes s")
    , @NamedQuery(name = "StampBookLikes.findByUserIdAndStampRallyId", query = "SELECT s FROM StampBookLikes s WHERE s.userId = :userId AND s.stamprally.stamprallyId = :stampRallyId")
    , @NamedQuery(name = "StampBookLikes.findByRegistrationDate", query = "SELECT s FROM StampBookLikes s WHERE s.registrationDate = :registrationDate")})
public class StampBookLikes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @JoinColumn(name = "stamprally", referencedColumnName = "stamprally_id")
    @ManyToOne
    private StampRallys stamprally;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public StampBookLikes() {
    }

    public StampBookLikes(Integer userId) {
        this.userId = userId;
    }

    public StampBookLikes(Users user, StampRallys stampRally) {
        userId = user.getUserId();
        this.users = user;
        this.stamprally = stampRally;
        registrationDate = new Date();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public StampRallys getStamprally() {
        return stamprally;
    }

    public void setStamprally(StampRallys stamprally) {
        this.stamprally = stamprally;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
