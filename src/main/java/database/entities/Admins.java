/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import database.entities.Users;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author b3314
 */
@Entity
@Table(name = "admins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admins.findAll", query = "SELECT a FROM Admins a")
    , @NamedQuery(name = "Admins.findByAdministratorId", query = "SELECT a FROM Admins a WHERE a.administratorId = :administratorId")
    , @NamedQuery(name = "Admins.findByRoginId", query = "SELECT a FROM Admins a WHERE a.roginId = :roginId")
    , @NamedQuery(name = "Admins.findByPassword", query = "SELECT a FROM Admins a WHERE a.password = :password")
    , @NamedQuery(name = "Admins.findByUserId", query = "SELECT a FROM Admins a WHERE a.userId = :userId")
    , @NamedQuery(name = "Admins.findByRegistrationDate", query = "SELECT a FROM Admins a WHERE a.registrationDate = :registrationDate")
    , @NamedQuery(name = "Admins.findByDeleteDate", query = "SELECT a FROM Admins a WHERE a.deleteDate = :deleteDate")})
public class Admins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "administrator_id")
    private Integer administratorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "rogin_id")
    private String roginId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "delete_date")
    @Temporal(TemporalType.DATE)
    private Date deleteDate;
    @JoinTable(name = "admins_users", joinColumns = {
        @JoinColumn(name = "administrator_id", referencedColumnName = "administrator_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToMany
    private Collection<Users> usersCollection;

    public Admins() {
    }

    public Admins(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public Admins(Integer administratorId, String roginId, String password, int userId, Date registrationDate, Date deleteDate) {
        this.administratorId = administratorId;
        this.roginId = roginId;
        this.password = password;
        this.userId = userId;
        this.registrationDate = registrationDate;
        this.deleteDate = deleteDate;
    }

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public String getRoginId() {
        return roginId;
    }

    public void setRoginId(String roginId) {
        this.roginId = roginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (administratorId != null ? administratorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admins)) {
            return false;
        }
        Admins other = (Admins) object;
        if ((this.administratorId == null && other.administratorId != null) || (this.administratorId != null && !this.administratorId.equals(other.administratorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Admins[ administratorId=" + administratorId + " ]";
    }
    
}
