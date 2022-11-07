/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import enumeration.RegionEnum;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author tylerwang
 */
@Entity
public class Fund implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String fundIdentifier;
    private String name;
    private int GRESBRating;
    private boolean inUNRPI;
    private boolean inTFCD;
    private double fvalue;
    private double greenValue;
    private double percentGreenByValue;
    //private FinancialQuarter quarter;
    private int fyear;
    private int fquarter;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)    
    private List<Asset> assets;    
    

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
        if (!(object instanceof Fund)) {
            return false;
        }
        Fund other = (Fund) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Fund[ id=" + id + " ]";
    }

    public String getFundIdentifier() {
        return fundIdentifier;
    }

    public void setFundIdentifier(String fundIdentifier) {
        this.fundIdentifier = fundIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGRESBRating() {
        return GRESBRating;
    }

    public void setGRESBRating(int GRESBRating) {
        this.GRESBRating = GRESBRating;
    }

    public boolean isInUNRPI() {
        return inUNRPI;
    }

    public void setInUNRPI(boolean inUNRPI) {
        this.inUNRPI = inUNRPI;
    }

    public boolean isInTFCD() {
        return inTFCD;
    }

    public void setInTFCD(boolean inTFCD) {
        this.inTFCD = inTFCD;
    }

    

    public double getGreenValue() {
        return greenValue;
    }

    public void setGreenValue(double greenValue) {
        this.greenValue = greenValue;
    }

    public double getFvalue() {
        return fvalue;
    }

    public void setFvalue(double fvalue) {
        this.fvalue = fvalue;
    }

    public int getFyear() {
        return fyear;
    }

    public void setFyear(int fyear) {
        this.fyear = fyear;
    }

    public int getFquarter() {
        return fquarter;
    }

    public void setFquarter(int fquarter) {
        this.fquarter = fquarter;
    }

    
    
    public double getPercentGreenByValue() {
        return percentGreenByValue;
    }

    public void setPercentGreenByValue(double percentGreenByValue) {
        this.percentGreenByValue = percentGreenByValue;
    }

    /*
    public FinancialQuarter getQuarter() {
        return quarter;
    }

    public void setQuarter(FinancialQuarter quarter) {
        this.quarter = quarter;
    }
*/

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

}
