/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author b3314
 */
@Entity
@Table(name = "tags")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tags.findAll", query = "SELECT t FROM Tags t")
    , @NamedQuery(name = "Tags.findByTagId", query = "SELECT t FROM Tags t WHERE t.tagId = :tagId")
    , @NamedQuery(name = "Tags.findByTagName", query = "SELECT t FROM Tags t WHERE t.tagName = :tagName")})
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tag_id")
    private Integer tagId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "tag_name")
    private String tagName;
    @JoinTable(name = "stamprally_tags", joinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")}, inverseJoinColumns = {
        @JoinColumn(name = "stamprally_id", referencedColumnName = "stamprally_id")})
    @ManyToMany
    private Collection<StampRallys> stampRallysCollection;

    public Tags() {
    }

    public Tags(Integer tagId) {
        this.tagId = tagId;
    }

    public Tags(Integer tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @XmlTransient
    public Collection<StampRallys> getStampRallysCollection() {
        return stampRallysCollection;
    }

    public void setStampRallysCollection(Collection<StampRallys> stampRallysCollection) {
        this.stampRallysCollection = stampRallysCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagId != null ? tagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tags)) {
            return false;
        }
        Tags other = (Tags) object;
        if ((this.tagId == null && other.tagId != null) || (this.tagId != null && !this.tagId.equals(other.tagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tags[ tagId=" + tagId + " ]";
    }
    
}
