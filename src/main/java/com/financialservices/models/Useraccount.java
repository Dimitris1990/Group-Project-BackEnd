package com.financialservices.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "useraccount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Useraccount.findAll", query = "SELECT u FROM Useraccount u"),
    @NamedQuery(name = "Useraccount.findById", query = "SELECT u FROM Useraccount u WHERE u.id = :id"),
    @NamedQuery(name = "Useraccount.findByBalance", query = "SELECT u FROM Useraccount u WHERE u.balance = :balance")})
public class Useraccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    public Useraccount() {
    }

    public Useraccount(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

//    @XmlTransient
//    public Set<Funding> getFundingSet() {
//        return fundingSet;
//    }
//
//    public void setFundingSet(Set<Funding> fundingSet) {
//        this.fundingSet = fundingSet;
//    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Useraccount)) {
            return false;
        }
        Useraccount other = (Useraccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Useraccount[ id=" + id + " ]";
    }
   
}
