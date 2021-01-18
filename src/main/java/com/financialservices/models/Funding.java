
package com.financialservices.models;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "funding")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funding.findAll", query = "SELECT f FROM Funding f")
    , @NamedQuery(name = "Funding.findByFid", query = "SELECT f FROM Funding f WHERE f.fid = :fid")
    , @NamedQuery(name = "Funding.findByFdate", query = "SELECT f FROM Funding f WHERE f.fdate = :fdate")
    , @NamedQuery(name = "Funding.findByDeposit", query = "SELECT f FROM Funding f WHERE f.deposit = :deposit")
    , @NamedQuery(name = "Funding.findByWithdrawal", query = "SELECT f FROM Funding f WHERE f.withdrawal = :withdrawal")})
public class Funding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fid")
    private Long fid;
    @Basic(optional = false)
    @Column(name = "fdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdate;
    @Column(name = "deposit")
    private BigDecimal deposit;
    @Column(name = "withdrawal")
    private BigDecimal withdrawal;
   

    
     @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id", referencedColumnName = "id")
     private Useraccount useraccount;

    public Funding() {
    }

    public Funding(Long fid) {
        this.fid = fid;
    }

    public Funding(Long fid, Date fdate) {
        this.fid = fid;
        this.fdate = fdate;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(BigDecimal withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Useraccount getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(Useraccount useraccount) {
        this.useraccount = useraccount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fid != null ? fid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Funding)) {
            return false;
        }
        Funding other = (Funding) object;
        if ((this.fid == null && other.fid != null) || (this.fid != null && !this.fid.equals(other.fid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Funding[ fid=" + fid + " ]";
    }
    
}
