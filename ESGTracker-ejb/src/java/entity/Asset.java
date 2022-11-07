/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import enumeration.RegionEnum;
import enumeration.SectorEnum;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author plant
 */
@Entity
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double avalue;
    private boolean isGreen;
    private String country;
    private String updateUser;
    private RegionEnum region;
    private SectorEnum sector;
    private int ayear;
    private int aquarter;
    
    //private FinancialQuarter quarter;
    
    private Long fundId;
    
    //private CsvFile csvFile;

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
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Asset[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvalue() {
        return avalue;
    }

    public void setAvalue(double avalue) {
        this.avalue = avalue;
    }

    public boolean isIsGreen() {
        return isGreen;
    }

    public void setIsGreen(boolean isGreen) {
        this.isGreen = isGreen;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }

    public SectorEnum getSector() {
        return sector;
    }

    public void setSector(SectorEnum sector) {
        this.sector = sector;
    }

    /*
    public FinancialQuarter getQuarter() {
        return quarter;
    }

    public void setQuarter(FinancialQuarter quarter) {
        this.quarter = quarter;
    }
    */

    public int getAyear() {
        return ayear;
    }

    public void setAyear(int ayear) {
        this.ayear = ayear;
    }

    public int getAquarter() {
        return aquarter;
    }

    public void setAquarter(int aquarter) {
        this.aquarter = aquarter;
    }
    
    

    public Long getFundId() {
        return fundId;
    }

    public void setFundId(Long fundId) {
        this.fundId = fundId;
    }

//    public CsvFile getCsvFile() {
//        return csvFile;
//    }
//
//    public void setCsvFile(CsvFile csvFile) {
//        this.csvFile = csvFile;
//    }
    
}
