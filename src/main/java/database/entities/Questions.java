/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import database.entities.StampRallys;
import database.entities.Users;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

/**
 *
 * @author b3314
 */
@Entity
@Table(name = "questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q")
    , @NamedQuery(name = "Questions.findByPostId", query = "SELECT q FROM Questions q WHERE q.postId = :postId")
    , @NamedQuery(name = "Questions.findByReplyId", query = "SELECT q FROM Questions q WHERE q.replyId = :replyId")
    , @NamedQuery(name = "Questions.findByComment", query = "SELECT q FROM Questions q WHERE q.comment = :comment")
    , @NamedQuery(name = "Questions.findByPostDate", query = "SELECT q FROM Questions q WHERE q.postDate = :postDate")
    , @NamedQuery(name = "Questions.findByDeleteDate", query = "SELECT q FROM Questions q WHERE q.deleteDate = :deleteDate")})
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "post_id")
    private Integer postId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reply_id")
    private int replyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "post_date")
    @Temporal(TemporalType.DATE)
    private Date postDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "delete_date")
    @Temporal(TemporalType.DATE)
    private Date deleteDate;
    @JoinTable(name = "likes", joinColumns = {
        @JoinColumn(name = "post_id", referencedColumnName = "post_id")}, inverseJoinColumns = {
        @JoinColumn(name = "iine_user_id", referencedColumnName = "user_id")})
    @ManyToMany
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private Collection<Reports> reportsCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;
    @JoinColumn(name = "stamprally_id", referencedColumnName = "stamprally_id")
    @ManyToOne(optional = false)
    private StampRallys stamprallyId;

    public Questions() {
    }

    public Questions(Integer postId) {
        this.postId = postId;
    }

    public Questions(Integer postId, int replyId, String comment, Date postDate, Date deleteDate) {
        this.postId = postId;
        this.replyId = replyId;
        this.comment = comment;
        this.postDate = postDate;
        this.deleteDate = deleteDate;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
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

    @XmlTransient
    public Collection<Reports> getReportsCollection() {
        return reportsCollection;
    }

    public void setReportsCollection(Collection<Reports> reportsCollection) {
        this.reportsCollection = reportsCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questions)) {
            return false;
        }
        Questions other = (Questions) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Questions[ postId=" + postId + " ]";
    }
    
}
