/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
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

/**
 *
 * @author amruta
 */
@Entity
@Table(name = "userglucosereading")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userglucosereading_1.findAll", query = "SELECT u FROM Userglucosereading_1 u")
    , @NamedQuery(name = "Userglucosereading_1.findByGlucoselevel", query = "SELECT u FROM Userglucosereading_1 u WHERE u.glucoselevel = :glucoselevel")
    , @NamedQuery(name = "Userglucosereading_1.findById", query = "SELECT u FROM Userglucosereading_1 u WHERE u.id = :id")
    , @NamedQuery(name = "Userglucosereading_1.findByRecordingtime", query = "SELECT u FROM Userglucosereading_1 u WHERE u.recordingtime = :recordingtime")})
public class Userglucosereading_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "glucoselevel")
    private BigInteger glucoselevel;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "recordingtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordingtime;
    @JoinColumn(name = "loginid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Userinfo_1 loginid;

    public Userglucosereading_1() {
    }

    public Userglucosereading_1(Integer id) {
        this.id = id;
    }

    public BigInteger getGlucoselevel() {
        return glucoselevel;
    }

    public void setGlucoselevel(BigInteger glucoselevel) {
        this.glucoselevel = glucoselevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRecordingtime() {
        return recordingtime;
    }

    public void setRecordingtime(Date recordingtime) {
        this.recordingtime = recordingtime;
    }

    public Userinfo_1 getLoginid() {
        return loginid;
    }

    public void setLoginid(Userinfo_1 loginid) {
        this.loginid = loginid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userglucosereading_1)) {
            return false;
        }
        Userglucosereading_1 other = (Userglucosereading_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.dao.Userglucosereading_1[ id=" + id + " ]";
    }
    
}
