/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Fund;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
    
    public FundManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        setFunds(fundSessionLocal.searchFunds(null));
    }
    
    public void addFund(ActionEvent evt) {
        Fund f = new Fund();
        f.setName(fundName);
        f.setFundIdentifier(fundIdentifier);
        f.setFyear(fyear);
        f.setFyear(fyear);
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
    
    public void deleteCustomer() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String fIdStr = params.get("fId");
        Long fId = Long.parseLong(fIdStr);
        try {
            fundSessionLocal.deleteFund(fId);
        } catch (Exception e) {
//show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to delete fund entry"));
            return;
        }
        context.addMessage(null, new FacesMessage("Success", "Successfully deleted fund entry"));
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
    
}
