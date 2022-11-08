/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Asset;
import entity.Fund;
import enumeration.RegionEnum;
import enumeration.SectorEnum;
import exception.NoResultException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import session.FundSessionLocal;

/**
 *
 * @author xindi
 */
@Named(value = "fundManagedBean")
@ViewScoped
public class FundManagedBean implements Serializable {

    @EJB
    private FundSessionLocal fundSessionLocal;

    private String fundIdentifier;
    private String fundName;
    private int fyear;
    private int fquarter;
    private int gresb;
    private boolean unrpi;
    private boolean tfcd;

    private List<Fund> funds;
    private Fund selectedFund;
    private Long fundId;
    
    private List<Asset> assets;
    
    private String assetName;
    private double avalue;
    private boolean isGreen;
    private String country;
    private int ayear;
    private int aquarter;
    private Long assetFundId;
    private Asset selectedAsset;
    private Long assetId;
    private String assetFundName;
    private String sector;
    private String region;

    public FundManagedBean() {
    }

    @PostConstruct
    public void init() {
        setFunds(fundSessionLocal.searchFunds(null));
        setAssets(fundSessionLocal.searchAssets(null));
    }

    public void addFund(ActionEvent evt) {
        Fund f = new Fund();
        f.setName(fundName);
        f.setFundIdentifier(fundIdentifier);
        f.setFyear(fyear);
        f.setFquarter(fquarter);
        f.setGRESBRating(gresb);
        f.setInTFCD(tfcd);
        f.setInUNRPI(unrpi);

        fundSessionLocal.createFund(f);
    }

    public void loadSelectedFund() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.selectedFund
                    = fundSessionLocal.getFund(fundId);
            assets = this.selectedFund.getAssets();
            fundName = this.selectedFund.getName();
            fundIdentifier = this.selectedFund.getFundIdentifier();
            fyear = this.selectedFund.getFyear();
            fquarter = this.selectedFund.getFquarter();
            gresb = this.selectedFund.getGRESBRating();
            tfcd = this.selectedFund.isInTFCD();
            unrpi = this.selectedFund.isInUNRPI();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to load fund"));
        }
    }

    public void updateFund(ActionEvent evt) {
        FacesContext context = FacesContext.getCurrentInstance();
        selectedFund.setFundIdentifier(fundIdentifier);
        selectedFund.setName(fundName);
        selectedFund.setGRESBRating(gresb);
        selectedFund.setFyear(fyear);
        selectedFund.setFquarter(fquarter);
        selectedFund.setInTFCD(tfcd);
        selectedFund.setInUNRPI(unrpi);

        try {
            fundSessionLocal.updateFund(selectedFund);
        } catch (Exception e) {
//show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to update fund"));
            return;
        }
//need to make sure reinitialize the customers collection
        init();
        context.addMessage(null, new FacesMessage("Success", "Successfully updated fund entry"));
    }

    public void deleteFund() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String fIdStr = params.get("fundId");
        Long fId = Long.parseLong(fIdStr);
        try {
            fundSessionLocal.deleteFund(fId);
        } catch (Exception e) {
//show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to delete fund"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully deleted fund entry"));
        init();
    }
    
    public void addAsset(ActionEvent evt) {
        FacesContext context = FacesContext.getCurrentInstance();
        Asset a = new Asset();
        a.setFundId(fundId);
        a.setAyear(fyear);
        a.setAquarter(fquarter);
        a.setName(assetName);
        a.setAvalue(avalue);
        a.setIsGreen(isGreen);
        a.setCountry(country);
        a.setFundName(fundName);
        a.setSector(sector);
        a.setRegion(region);

        try {
            fundSessionLocal.createAsset(a);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to create asset"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully added asset"));
        init();
    }
    
    public void loadSelectedAsset() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.selectedAsset
                    = fundSessionLocal.getAsset(assetId);
            assetName = this.selectedAsset.getName();            
            avalue = this.selectedAsset.getAvalue();
            ayear = this.selectedAsset.getAyear();
            aquarter = this.selectedAsset.getAquarter();
            assetFundId = this.selectedAsset.getFundId();
            isGreen = this.selectedAsset.isIsGreen();
            country = this.selectedAsset.getCountry();
            assetFundName = this.selectedAsset.getFundName();
            sector = this.selectedAsset.getSector();
            region = this.selectedAsset.getRegion();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to load asset"));
        }
    }

    public void updateAsset(ActionEvent evt) {
        FacesContext context = FacesContext.getCurrentInstance();
        selectedAsset.setName(assetName);
        selectedAsset.setAvalue(avalue);
        selectedAsset.setAyear(ayear);
        selectedAsset.setAquarter(aquarter);
        selectedAsset.setFundId(assetFundId);
        selectedAsset.setFundName(assetFundName);
        selectedAsset.setIsGreen(isGreen);
        selectedAsset.setCountry(country);
        selectedAsset.setRegion(region);
        selectedAsset.setSector(sector);

        try {
            fundSessionLocal.updateAsset(selectedAsset);
        } catch (Exception e) {
//show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to update asset"));
            return;
        }
//need to make sure reinitialize the customers collection
        init();
        context.addMessage(null, new FacesMessage("Success", "Successfully updated asset entry"));
    }

    public void deleteAsset() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String aIdStr = params.get("assetId");
        Long aId = Long.parseLong(aIdStr);
        try {
            fundSessionLocal.deleteAsset(aId);
        } catch (Exception e) {
//show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to delete asset"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully deleted asset entry"));
        init();
    }

    public FundSessionLocal getFundSessionLocal() {
        return fundSessionLocal;
    }

    public void setFundSessionLocal(FundSessionLocal fundSessionLocal) {
        this.fundSessionLocal = fundSessionLocal;
    }

    public String getFundIdentifier() {
        return fundIdentifier;
    }

    public void setFundIdentifier(String fundIdentifier) {
        this.fundIdentifier = fundIdentifier;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
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

    public int getGresb() {
        return gresb;
    }

    public void setGresb(int gresb) {
        this.gresb = gresb;
    }

    public boolean isUnrpi() {
        return unrpi;
    }

    public void setUnrpi(boolean unrpi) {
        this.unrpi = unrpi;
    }

    public boolean isTfcd() {
        return tfcd;
    }

    public void setTfcd(boolean tfcd) {
        this.tfcd = tfcd;
    }

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }

    public Fund getSelectedFund() {
        return selectedFund;
    }

    public void setSelectedFund(Fund selectedFund) {
        this.selectedFund = selectedFund;
    }

    public Long getFundId() {
        return fundId;
    }

    public void setFundId(Long fundId) {
        this.fundId = fundId;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
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

    public Asset getSelectedAsset() {
        return selectedAsset;
    }

    public void setSelectedAsset(Asset selectedAsset) {
        this.selectedAsset = selectedAsset;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

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

    public Long getAssetFundId() {
        return assetFundId;
    }

    public void setAssetFundId(Long assetFundId) {
        this.assetFundId = assetFundId;
    }

    public String getAssetFundName() {
        return assetFundName;
    }

    public void setAssetFundName(String assetFundName) {
        this.assetFundName = assetFundName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
