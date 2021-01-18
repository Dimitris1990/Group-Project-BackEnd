
package com.financialservices.models;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "portfolio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Portfolio.findAll", query = "SELECT p FROM Portfolio p"),
    @NamedQuery(name = "Portfolio.findByPid", query = "SELECT p FROM Portfolio p WHERE p.pid = :pid"),
    @NamedQuery(name = "Portfolio.findByNumShares", query = "SELECT p FROM Portfolio p WHERE p.numShares = :numShares"),
    @NamedQuery(name = "Portfolio.findBySymbol", query = "SELECT p FROM Portfolio p WHERE p.symbol = :symbol"),
    @NamedQuery(name = "Portfolio.findByAvgPrice", query = "SELECT p FROM Portfolio p WHERE p.avgPrice = :avgPrice")})
public class Portfolio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pid")
    private Long pid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_shares")
    private int numShares;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "symbol")
    private String symbol;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "avg_price")
    private BigDecimal avgPrice;
    
    
    @JoinColumn(name = "id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Useraccount useraccount;

    public Portfolio() {
    }

    public Portfolio(Long pid) {
        this.pid = pid;
    }

    public Portfolio(Long pid, int numShares, String symbol, BigDecimal avgPrice) {
        this.pid = pid;
        this.numShares = numShares;
        this.symbol = symbol;
        this.avgPrice = avgPrice;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public int getNumShares() {
        return numShares;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
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
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Portfolio)) {
            return false;
        }
        Portfolio other = (Portfolio) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Portfolio[ pid=" + pid + " ]";
    }

}
