/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author tylerwang
 */
@Entity
public class CsvFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Date dateUpdated;
    private Date dateOfInformation;
    private String comments;
    
    //private FinancialQuarter quarter;
    
    //private List<Asset> assets;
    
    //private FundUser fundUser;
    
    //private Fund fund;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof CsvFile)) {
            return false;
        }
        CsvFile other = (CsvFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CsvFile[ id=" + id + " ]";
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateOfInformation() {
        return dateOfInformation;
    }

    public void setDateOfInformation(Date dateOfInformation) {
        this.dateOfInformation = dateOfInformation;
    }
    
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

//    public FundUser getFundUser() {
//        return fundUser;
//    }
//
//    public void setFundUser(FundUser fundUser) {
//        this.fundUser = fundUser;
//    }
//
//    public void setQuarter(FinancialQuarter quarter) {
//        this.quarter = quarter;
//    }
//    
//    public FinancialQuarter getQuarter() {
//        return quarter;
//    }
//
//    public List<Asset> getAssets() {
//        return assets;
//    }
//
//    public void setAssets(List<Asset> assets) {
//        this.assets = assets;
//    }
//
//    public FundUser getUser() {
//        return fundUser;
//    }
//
//    public void setUser(FundUser fundUser) {
//        this.fundUser = fundUser;
//    }
//
//    public Fund getFund() {
//        return fund;
//    }
//
//    public void setFund(Fund fund) {
//        this.fund = fund;
//    }
    
}
