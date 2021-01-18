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
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o") ,
    @NamedQuery(name = "Orders.findByOid", query = "SELECT o FROM Orders o WHERE o.oid = :oid"),
    @NamedQuery(name = "Orders.findByNumShares", query = "SELECT o FROM Orders o WHERE o.numShares = :numShares"),
    @NamedQuery(name = "Orders.findByCurrentPrice", query = "SELECT o FROM Orders o WHERE o.currentPrice = :currentPrice") ,
    @NamedQuery(name = "Orders.findByTimestmp", query = "SELECT o FROM Orders o WHERE o.timestmp = :timestmp"),
    @NamedQuery(name = "Orders.findBySymbol", query = "SELECT o FROM Orders o WHERE o.symbol = :symbol"),
    @NamedQuery(name = "Orders.findByActv", query = "SELECT o FROM Orders o WHERE o.actv = :actv"),
    @NamedQuery(name = "Orders.findByCommission", query = "SELECT o FROM Orders o WHERE o.commission = :commission") ,
    @NamedQuery(name = "Orders.findByOrdertype", query = "SELECT o FROM Orders o WHERE o.ordertype = :ordertype")})

public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oid")
    private Long oid;
    @Basic(optional = false)
    @Column(name = "num_shares")
    private Long numShares;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "current_price")
    private BigDecimal currentPrice;
    @Basic(optional = false)
    @Column(name = "timestmp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestmp;
    @Basic(optional = false)
    @Column(name = "actv")
    private Boolean actv;
    @Column(name = "commission")
    private BigDecimal commission;
    @Column(name = "symbol")
    private String symbol;

    @Column(name = "ordertype")
    private int ordertype;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Useraccount useraccount;

    public Orders() {
    }

    public Orders(Long oid) {
        this.oid = oid;
    }

    public Orders(Long oid, Long numShares, BigDecimal currentPrice, Date timestmp, Boolean actv,
            BigDecimal commission, String symbol, int ordertype, Useraccount useraccount) {
        this.oid = oid;
        this.numShares = numShares;
        this.currentPrice = currentPrice;
        this.timestmp = timestmp;
        this.actv = actv;
        this.commission = commission;
        this.symbol = symbol;
        this.ordertype = ordertype;
        this.useraccount = useraccount;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getNumShares() {
        return numShares;
    }

    public void setNumShares(Long numShares) {
        this.numShares = numShares;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getTimestmp() {
        return timestmp;
    }

    public void setTimestmp(Date timestmp) {
        this.timestmp = timestmp;
    }

    public Boolean getActv() {
        return actv;
    }

    public void setActv(Boolean actv) {
        this.actv = actv;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Useraccount getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(Useraccount useraccount) {
        this.useraccount = useraccount;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Orders[ oid=" + oid + " ]";
    }

}
