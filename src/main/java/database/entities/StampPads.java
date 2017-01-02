/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "stamp_pads")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StampPads.findAll", query = "SELECT s FROM StampPads s")
    , @NamedQuery(name = "StampPads.findByStamptableId", query = "SELECT s FROM StampPads s WHERE s.stamptableId = :stamptableId")
    , @NamedQuery(name = "StampPads.findByStampAddress", query = "SELECT s FROM StampPads s WHERE s.stampAddress = :stampAddress")
    , @NamedQuery(name = "StampPads.findByLatitude", query = "SELECT s FROM StampPads s WHERE s.latitude = :latitude")
    , @NamedQuery(name = "StampPads.findByLongitude", query = "SELECT s FROM StampPads s WHERE s.longitude = :longitude")
    , @NamedQuery(name = "StampPads.findByStampcreateDate", query = "SELECT s FROM StampPads s WHERE s.stampcreateDate = :stampcreateDate")})
public class StampPads implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stamptable_id")
    private Integer stamptableId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stamp_address")
    private String stampAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stampcreate_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stampcreateDate;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stampPads")
    private List<Stamps> stampsList = new ArrayList<>();

    public StampPads() {
    }
    
    public StampPads(Map<String, Object> stampData) {
        stampAddress = "default";
        latitude = (double) stampData.get("latitude");
        longitude = (double) stampData.get("longitude");
        stampcreateDate = new Date((long) stampData.get("createDate"));
    }

    public Integer getStamptableId() {
        return stamptableId;
    }

    public void setStamptableId(Integer stamptableId) {
        this.stamptableId = stamptableId;
    }

    public String getStampAddress() {
        return stampAddress;
    }

    public void setStampAddress(String stampAddress) {
        this.stampAddress = stampAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getStampcreateDate() {
        return stampcreateDate;
    }

    public void setStampcreateDate(Date stampcreateDate) {
        this.stampcreateDate = stampcreateDate;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<Stamps> getStampsList() {
        return stampsList;
    }

    public void setStampsList(List<Stamps> stampsCollection) {
        this.stampsList = stampsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stamptableId != null ? stamptableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StampPads)) {
            return false;
        }
        StampPads other = (StampPads) object;
        if ((this.stamptableId == null && other.stamptableId != null) || (this.stamptableId != null && !this.stamptableId.equals(other.stamptableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StampPads[ stamptableId=" + stamptableId + " ]";
    }
    
}
