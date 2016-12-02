/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import database.entities.StampRallys;
import database.entities.Stamps;
import database.entities.Users;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b3314
 */
@Entity
@Table(name = "reports")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reports.findAll", query = "SELECT r FROM Reports r")
    , @NamedQuery(name = "Reports.findByReportId", query = "SELECT r FROM Reports r WHERE r.reportId = :reportId")
    , @NamedQuery(name = "Reports.findByReportType", query = "SELECT r FROM Reports r WHERE r.reportType = :reportType")})
public class Reports implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "report_id")
    private Integer reportId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "report_type")
    private short reportType;
    @JoinColumn(name = "reported_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users reportedUserId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;
    @JoinColumn(name = "stamprally_id", referencedColumnName = "stamprally_id")
    @ManyToOne(optional = false)
    private StampRallys stamprallyId;
    @JoinColumn(name = "stamp_id", referencedColumnName = "stamp_id")
    @ManyToOne(optional = false)
    private Stamps stampId;
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @ManyToOne(optional = false)
    private Questions postId;

    public Reports() {
    }

    public Reports(Integer reportId) {
        this.reportId = reportId;
    }

    public Reports(Integer reportId, short reportType) {
        this.reportId = reportId;
        this.reportType = reportType;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public short getReportType() {
        return reportType;
    }

    public void setReportType(short reportType) {
        this.reportType = reportType;
    }

    public Users getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(Users reportedUserId) {
        this.reportedUserId = reportedUserId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public StampRallys getStamprallyId() {
        return stamprallyId;
    }

    public void setStamprallyId(StampRallys stamprallyId) {
        this.stamprallyId = stamprallyId;
    }

    public Stamps getStampId() {
        return stampId;
    }

    public void setStampId(Stamps stampId) {
        this.stampId = stampId;
    }

    public Questions getPostId() {
        return postId;
    }

    public void setPostId(Questions postId) {
        this.postId = postId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportId != null ? reportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reports)) {
            return false;
        }
        Reports other = (Reports) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Reports[ reportId=" + reportId + " ]";
    }
    
}
