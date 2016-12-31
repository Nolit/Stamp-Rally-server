/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import database.entities.Reports;
import database.entities.Activities;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
import utilities.ImageSaver;

/**
 *
 * @author b3314
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@Table(name = "stamps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stamps.findAll", query = "SELECT s FROM Stamps s")
    , @NamedQuery(name = "Stamps.findByStampId", query = "SELECT s FROM Stamps s WHERE s.stampId = :stampId")
    , @NamedQuery(name = "Stamps.findByStampDate", query = "SELECT s FROM Stamps s WHERE s.stampDate = :stampDate")})
public class Stamps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stamp_id")
    private Integer stampId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "picture_pass")
    private String picturePass;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "stamp_name")
    private String stampName;
    @Basic(optional = true)
    @Column(name = "stamp_comment")
    private String stampComment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stamp_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stampDate;
    @ManyToMany(mappedBy = "structurePads")
    private Collection<StampRallys> stampRallysCollection;
    @JoinTable(name = "stamp_get_destinations", joinColumns = {
        @JoinColumn(name = "stamp_id", referencedColumnName = "stamp_id")}, inverseJoinColumns = {
        @JoinColumn(name = "stamprally_id", referencedColumnName = "stamprally_id")})
    @ManyToMany
    private Collection<StampRallys> stampRallysCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stampId")
    private Collection<Reports> reportsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stampId")
    private Collection<Activities> activitiesCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;
    @JoinColumn(name = "stamptable_id", referencedColumnName = "stamptable_id")
    @ManyToOne(optional = false)
    private StampPads stampPads;

    public Stamps() {
    }
    
    public Stamps(Map<String, Object> stampData) {
        stampName = (String) stampData.get("title");
        stampComment = (String) stampData.get("note");
        stampDate = new Date((long) stampData.get("createDate"));
        picturePass = "default";
    }

    public Integer getStampId() {
        return stampId;
    }

    public void setStampId(Integer stampId) {
        this.stampId = stampId;
    }

    public String getPicturePass() {
        return picturePass;
    }

    public void setPicturePass(String picturePass) {
        this.picturePass = picturePass;
    }

    public String getStampName() {
        return stampName;
    }

    public void setStampName(String stampName) {
        this.stampName = stampName;
    }

    public String getStampComment() {
        return stampComment;
    }

    public void setStampComment(String stampComment) {
        this.stampComment = stampComment;
    }

    public Date getStampDate() {
        return stampDate;
    }

    public void setStampDate(Date stampDate) {
        this.stampDate = stampDate;
    }

    @XmlTransient
    public Collection<StampRallys> getStampRallysCollection() {
        return stampRallysCollection;
    }

    public void setStampRallysCollection(Collection<StampRallys> stampRallysCollection) {
        this.stampRallysCollection = stampRallysCollection;
    }

    @XmlTransient
    public Collection<StampRallys> getStampRallysCollection1() {
        return stampRallysCollection1;
    }

    public void setStampRallysCollection1(Collection<StampRallys> stampRallysCollection1) {
        this.stampRallysCollection1 = stampRallysCollection1;
    }

    @XmlTransient
    public Collection<Reports> getReportsCollection() {
        return reportsCollection;
    }

    public void setReportsCollection(Collection<Reports> reportsCollection) {
        this.reportsCollection = reportsCollection;
    }

    @XmlTransient
    public Collection<Activities> getActivitiesCollection() {
        return activitiesCollection;
    }

    public void setActivitiesCollection(Collection<Activities> activitiesCollection) {
        this.activitiesCollection = activitiesCollection;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public StampPads getStampPads() {
        return stampPads;
    }

    public void setStampPads(StampPads stampPads) {
        this.stampPads = stampPads;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stampId != null ? stampId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stamps)) {
            return false;
        }
        Stamps other = (Stamps) object;
        if ((this.stampId == null && other.stampId != null) || (this.stampId != null && !this.stampId.equals(other.stampId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Stamps[ stampId=" + stampId + " ]";
    }
    
}
