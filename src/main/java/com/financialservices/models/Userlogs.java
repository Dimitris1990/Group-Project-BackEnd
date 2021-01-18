package com.financialservices.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "userlogs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userlogs.findAll", query = "SELECT u FROM Userlogs u")
    , @NamedQuery(name = "Userlogs.findByLid", query = "SELECT u FROM Userlogs u WHERE u.lid = :lid")
    , @NamedQuery(name = "Userlogs.findByUlogs", query = "SELECT u FROM Userlogs u WHERE u.ulogs = :ulogs")})
public class Userlogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lid")
    private Long lid;
    @Basic(optional = false)
    @Column(name = "ulogs")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ulogs;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    public Userlogs() {
    }

    public Userlogs(Long lid) {
        this.lid = lid;
    }

    public Userlogs(Long lid, Date ulogs) {
        this.lid = lid;
        this.ulogs = ulogs;
    }

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public Date getUlogs() {
        return ulogs;
    }

    public void setUlogs(Date ulogs) {
        this.ulogs = ulogs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lid != null ? lid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userlogs)) {
            return false;
        }
        Userlogs other = (Userlogs) object;
        if ((this.lid == null && other.lid != null) || (this.lid != null && !this.lid.equals(other.lid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Userlogs[ lid=" + lid + " ]";
    }

}
