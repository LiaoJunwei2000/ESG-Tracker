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
    
    public FilterManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        funds = fundManagedBean.getFunds();
    }
    
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
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
    
}
