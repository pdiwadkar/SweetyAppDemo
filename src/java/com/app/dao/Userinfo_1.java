/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author amruta
 */
@Entity
@Table(name = "userinfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userinfo_1.findAll", query = "SELECT u FROM Userinfo_1 u")
    , @NamedQuery(name = "Userinfo_1.findByLoginname", query = "SELECT u FROM Userinfo_1 u WHERE u.loginname = :loginname")
    , @NamedQuery(name = "Userinfo_1.findByPassword", query = "SELECT u FROM Userinfo_1 u WHERE u.password = :password")
    , @NamedQuery(name = "Userinfo_1.findById", query = "SELECT u FROM Userinfo_1 u WHERE u.id = :id")})
public class Userinfo_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "loginname")
    private String loginname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loginid")
    private Collection<Userglucosereading_1> userglucosereadingCollection;

    public Userinfo_1() {
    }

    public Userinfo_1(String loginname) {
        this.loginname = loginname;
    }

    public Userinfo_1(String loginname, String password, int id) {
        this.loginname = loginname;
        this.password = password;
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<Userglucosereading_1> getUserglucosereadingCollection() {
        return userglucosereadingCollection;
    }

    public void setUserglucosereadingCollection(Collection<Userglucosereading_1> userglucosereadingCollection) {
        this.userglucosereadingCollection = userglucosereadingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginname != null ? loginname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userinfo_1)) {
            return false;
        }
        Userinfo_1 other = (Userinfo_1) object;
        if ((this.loginname == null && other.loginname != null) || (this.loginname != null && !this.loginname.equals(other.loginname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.dao.Userinfo_1[ loginname=" + loginname + " ]";
    }
    
}
