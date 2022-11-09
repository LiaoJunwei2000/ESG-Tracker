/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedbean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.FundSessionLocal;

/**
 *
 * @author plant
 */
@Named(value = "testBean")
@ViewScoped
public class testBean implements Serializable {

    @EJB
    private FundSessionLocal fundSessionLocal;

    private List<Double> portGresb;
    private List<Double> portGreenPct;
    private List<Double> fundGresb;
    private List<Double> fundGreenPct;
    private List<Double> regionGresb;
    private List<Double> sectorGresb;

    /**
     * Creates a new instance of testBean
     */
    public testBean() {
        List<Double> data = new ArrayList<Double>();
        data.add((Double) 1.0);
        data.add((Double) 2.0);
        data.add((Double) 3.0);
        data.add((Double) 4.0);
        data.add((Double) 5.0);
        data.add((Double) 6.0);
        data.add((Double) 7.0);
        data.add((Double) 8.0);
        data.add((Double) 9.0);
        data.add((Double) 10.0);
        setPortGresb(data);
//        setPortGresb(fundSessionLocal.getPortGresbList());
    }

    /**
     * @return the fundSessionLocal
     */
    public FundSessionLocal getFundSessionLocal() {
        return fundSessionLocal;
    }

    /**
     * @param fundSessionLocal the fundSessionLocal to set
     */
    public void setFundSessionLocal(FundSessionLocal fundSessionLocal) {
        this.fundSessionLocal = fundSessionLocal;
    }

    /**
     * @return the portGresb
     */
    public List<Double> getPortGresb() {
        return portGresb;
    }

    /**
     * @param portGresb the portGresb to set
     */
    public void setPortGresb(List<Double> portGresb) {
        this.portGresb = portGresb;
    }

    /**
     * @return the portGreenPct
     */
    public List<Double> getPortGreenPct() {
        return portGreenPct;
    }

    /**
     * @param portGreenPct the portGreenPct to set
     */
    public void setPortGreenPct(List<Double> portGreenPct) {
        this.portGreenPct = portGreenPct;
    }

    /**
     * @return the fundGresb
     */
    public List<Double> getFundGresb() {
        return fundGresb;
    }

    /**
     * @param fundGresb the fundGresb to set
     */
    public void setFundGresb(List<Double> fundGresb) {
        this.fundGresb = fundGresb;
    }

    /**
     * @return the fundGreenPct
     */
    public List<Double> getFundGreenPct() {
        return fundGreenPct;
    }

    /**
     * @param fundGreenPct the fundGreenPct to set
     */
    public void setFundGreenPct(List<Double> fundGreenPct) {
        this.fundGreenPct = fundGreenPct;
    }

    /**
     * @return the regionGresb
     */
    public List<Double> getRegionGresb() {
        return regionGresb;
    }

    /**
     * @param regionGresb the regionGresb to set
     */
    public void setRegionGresb(List<Double> regionGresb) {
        this.regionGresb = regionGresb;
    }

    /**
     * @return the sectorGresb
     */
    public List<Double> getSectorGresb() {
        return sectorGresb;
    }

    /**
     * @param sectorGresb the sectorGresb to set
     */
    public void setSectorGresb(List<Double> sectorGresb) {
        this.sectorGresb = sectorGresb;
    }

}
