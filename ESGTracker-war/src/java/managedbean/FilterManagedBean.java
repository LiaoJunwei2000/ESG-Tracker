/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Asset;
import entity.Fund;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

/**
 *
 * @author xindi
 */
@Named(value = "filterManagedBean")
@ViewScoped
public class FilterManagedBean implements Serializable {
    
    @Inject
    private FundManagedBean fundManagedBean;

    private List<Fund> funds;
    private List<Fund> filteredFunds;
    
    private List<Asset> assets;
    private List<Asset> filteredAssets;
    
    public FilterManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        funds = fundManagedBean.getFunds();
        assets = fundManagedBean.getAssets();
    }
    
    public boolean globalFilterFunctionFunds(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Fund fund = (Fund) value;
        return fund.getName().toLowerCase().contains(filterText)
                || fund.getFundIdentifier().toLowerCase().contains(filterText)
                || fund.getFyear() == filterInt
                || fund.getFquarter() == filterInt
                || fund.getFvalue() == filterInt
                || (fund.isInTFCD() ? "Yes" : "No").contains(filterText)
                || (fund.isInUNRPI() ? "Yes" : "No").contains(filterText)
                || fund.getGRESBRating() == filterInt
                || fund.getPercentGreenByValue() == filterInt
                || fund.getGreenValue() == filterInt;              
    }
    
    public boolean globalFilterFunctionAssets(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Asset asset = (Asset) value;
        return asset.getName().toLowerCase().contains(filterText)
                || asset.getFundName().toLowerCase().contains(filterText)
                || asset.getAyear() == filterInt
                || asset.getAquarter() == filterInt
                || asset.getAvalue() == filterInt
                || asset.getCountry().toLowerCase().contains(filterText)
                || asset.getRegion().toLowerCase().contains(filterText)
                || asset.getSector().toLowerCase().contains(filterText)
                || (asset.isIsGreen() ? "Yes" : "No").contains(filterText);             
    }
    
    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }

    public List<Fund> getFilteredFunds() {
        return filteredFunds;
    }

    public void setFilteredFunds(List<Fund> filteredFunds) {
        this.filteredFunds = filteredFunds;
    }

    public FundManagedBean getFundManagedBean() {
        return fundManagedBean;
    }

    public void setFundManagedBean(FundManagedBean fundManagedBean) {
        this.fundManagedBean = fundManagedBean;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Asset> getFilteredAssets() {
        return filteredAssets;
    }

    public void setFilteredAssets(List<Asset> filteredAssets) {
        this.filteredAssets = filteredAssets;
    }
    
    
}
