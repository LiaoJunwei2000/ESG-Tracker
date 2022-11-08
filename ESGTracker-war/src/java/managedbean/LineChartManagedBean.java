/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedbean;

import com.sun.corba.se.impl.io.IIOPInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author plant
 */
@Named(value = "lineChartManagedBean")
@ViewScoped
public class LineChartManagedBean implements Serializable{

    private LineChartModel model;
    private LineChartModel portGresbModel;
    private LineChartModel portGreenPctModel;
    private LineChartModel fundGresbModel;
    private LineChartModel fundGreenPctModel;
    private LineChartModel regionGresbModel;
    private LineChartModel sectorGresbModel;
    private List<Double> portGresb;
    private List<Double> portGreenPct;
    private List<Double> fundGresb;
    private List<Double> fundGreenPct;
    private List<Double> regionGresb;
    private List<Double> sectorGresb;

    private String fundIdentifier;

    private List<Double> testData;

    public LineChartManagedBean() {

    }

    @PostConstruct
    public void init() {
        dataInitialization();
        createPortGresbModel();
        createPortGreenPctModel();
        createFundGresbModel();
        createFundGreenPctModel();
        createRegionGresbModel();
        createSectorGresbModel();

    }

    private void dataInitialization() {
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
        setTestData(data);
        setFundIdentifier("Fund A");
    }

    public void createPortGresbModel() {

//      setPortGresb(chartSessionBean.portGresb);
        setPortGresb(getTestData());

        setPortGresbModel(new LineChartModel());
        ChartSeries scores = new ChartSeries();
        scores.setLabel("Portfolio");
        scores.set("2020 Q2", getPortGresb().get(9));
        scores.set("2020 Q3", getPortGresb().get(8));
        scores.set("2020 Q4", getPortGresb().get(7));
        scores.set("2021 Q1", getPortGresb().get(6));
        scores.set("2021 Q2", getPortGresb().get(5));
        scores.set("2021 Q3", getPortGresb().get(4));
        scores.set("2021 Q4", getPortGresb().get(3));
        scores.set("2022 Q1", getPortGresb().get(2));
        scores.set("2022 Q2", getPortGresb().get(1));
        scores.set("2022 Q3", getPortGresb().get(0));
        getPortGresbModel().addSeries(scores);

        getPortGresbModel().setTitle("Portfolio Quarterly GRESB");
        getPortGresbModel().setLegendPosition("e");
        getPortGresbModel().setShowPointLabels(true);
        getPortGresbModel().getAxes().put(AxisType.X, new CategoryAxis("Quarter"));
        Axis yAxis = getPortGresbModel().getAxis(AxisType.Y);
        yAxis.setLabel("GRESB Score");

    }

    public void createPortGreenPctModel() {
        setPortGreenPct(getTestData());
        setPortGreenPctModel(new LineChartModel());
        ChartSeries scores = new ChartSeries();
        scores.setLabel("Portfolio");
        scores.set("2020 Q2", getPortGreenPct().get(9));
        scores.set("2020 Q3", getPortGreenPct().get(8));
        scores.set("2020 Q4", getPortGreenPct().get(7));
        scores.set("2021 Q1", getPortGreenPct().get(6));
        scores.set("2021 Q2", getPortGreenPct().get(5));
        scores.set("2021 Q3", getPortGreenPct().get(4));
        scores.set("2021 Q4", getPortGreenPct().get(3));
        scores.set("2022 Q1", getPortGreenPct().get(2));
        scores.set("2022 Q2", getPortGreenPct().get(1));
        scores.set("2022 Q3", getPortGreenPct().get(0));
        getPortGreenPctModel().addSeries(scores);

        getPortGreenPctModel().setTitle("Portfolio Green Building %");
        getPortGreenPctModel().setLegendPosition("e");
        getPortGreenPctModel().setShowPointLabels(true);
        getPortGreenPctModel().getAxes().put(AxisType.X, new CategoryAxis("Quarter"));
        Axis yAxis = getPortGreenPctModel().getAxis(AxisType.Y);
        yAxis.setLabel("Green Building %");

    }

    public void createFundGresbModel() {
        setFundGresb(getTestData());
        setFundGresbModel(new LineChartModel());
        ChartSeries scores = new ChartSeries();
        scores.setLabel(fundIdentifier);
        scores.set("2020 Q2", getFundGresb().get(9));
        scores.set("2020 Q3", getFundGresb().get(8));
        scores.set("2020 Q4", getFundGresb().get(7));
        scores.set("2021 Q1", getFundGresb().get(6));
        scores.set("2021 Q2", getFundGresb().get(5));
        scores.set("2021 Q3", getFundGresb().get(4));
        scores.set("2021 Q4", getFundGresb().get(3));
        scores.set("2022 Q1", getFundGresb().get(2));
        scores.set("2022 Q2", getFundGresb().get(1));
        scores.set("2022 Q3", getFundGresb().get(0));
        getFundGresbModel().addSeries(scores);

        getFundGresbModel().setTitle(fundIdentifier + " Quarterly GRESB");
        getFundGresbModel().setLegendPosition("e");
        getFundGresbModel().setShowPointLabels(true);
        getFundGresbModel().getAxes().put(AxisType.X, new CategoryAxis("Quarter"));
        Axis yAxis = getFundGresbModel().getAxis(AxisType.Y);
        yAxis.setLabel("GRESB Score");
    }

    public void createFundGreenPctModel() {
        setFundGreenPct(getTestData());
        setFundGreenPctModel(new LineChartModel());
        ChartSeries scores = new ChartSeries();
        scores.setLabel(fundIdentifier);
        scores.set("2020 Q2", getFundGreenPct().get(9));
        scores.set("2020 Q3", getFundGreenPct().get(8));
        scores.set("2020 Q4", getFundGreenPct().get(7));
        scores.set("2021 Q1", getFundGreenPct().get(6));
        scores.set("2021 Q2", getFundGreenPct().get(5));
        scores.set("2021 Q3", getFundGreenPct().get(4));
        scores.set("2021 Q4", getFundGreenPct().get(3));
        scores.set("2022 Q1", getFundGreenPct().get(2));
        scores.set("2022 Q2", getFundGreenPct().get(1));
        scores.set("2022 Q3", getFundGreenPct().get(0));
        getFundGreenPctModel().addSeries(scores);

        getFundGreenPctModel().setTitle(fundIdentifier + " Green Building %");
        getFundGreenPctModel().setLegendPosition("e");
        getFundGreenPctModel().setShowPointLabels(true);
        getFundGreenPctModel().getAxes().put(AxisType.X, new CategoryAxis("Quarter"));
        Axis yAxis = getFundGreenPctModel().getAxis(AxisType.Y);
        yAxis.setLabel("Green Building %");
    }
    
    

    public void createRegionGresbModel() {
        setRegionGresb(getTestData());
        setRegionGresbModel(new LineChartModel());
        ChartSeries scores = new ChartSeries();
        scores.setLabel("Regions");
        scores.set("AFR", getRegionGresb().get(4));
        scores.set("EUR", getRegionGresb().get(3));
        scores.set("SA", getRegionGresb().get(2));
        scores.set("NA", getRegionGresb().get(1));
        scores.set("APAC", getRegionGresb().get(0));
        getRegionGresbModel().addSeries(scores);

        getRegionGresbModel().setTitle("Region Quarterly GRESB");
        getRegionGresbModel().setLegendPosition("e");
        getRegionGresbModel().setShowPointLabels(true);
        getRegionGresbModel().getAxes().put(AxisType.X, new CategoryAxis("Region"));
        Axis yAxis = getRegionGresbModel().getAxis(AxisType.Y);
        yAxis.setLabel("GRESB Score");
    }

    public void createSectorGresbModel() {
        setSectorGresb(getTestData());
        setSectorGresbModel(new LineChartModel());
        ChartSeries scores = new ChartSeries();
        scores.setLabel("Sectors");
        scores.set("Other", getSectorGresb().get(3));
        scores.set("Industrial", getSectorGresb().get(2));
        scores.set("Office", getSectorGresb().get(1));
        scores.set("Residential", getSectorGresb().get(0));
        getSectorGresbModel().addSeries(scores);

        getSectorGresbModel().setTitle("Sector Quarterly GRESB");
        getSectorGresbModel().setLegendPosition("e");
        getSectorGresbModel().setShowPointLabels(true);
        getSectorGresbModel().getAxes().put(AxisType.X, new CategoryAxis("Sector"));
        Axis yAxis = getSectorGresbModel().getAxis(AxisType.Y);
        yAxis.setLabel("GRESB Score");
    }

    public LineChartModel getPortGresbModel() {
        return portGresbModel;
    }

    public LineChartModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(LineChartModel model) {
        this.model = model;
    }

    /**
     * @param portGresbModel the portGresbModel to set
     */
    public void setPortGresbModel(LineChartModel portGresbModel) {
        this.portGresbModel = portGresbModel;
    }

    /**
     * @return the portGreenPctModel
     */
    public LineChartModel getPortGreenPctModel() {
        return portGreenPctModel;
    }

    /**
     * @param portGreenPctModel the portGreenPctModel to set
     */
    public void setPortGreenPctModel(LineChartModel portGreenPctModel) {
        this.portGreenPctModel = portGreenPctModel;
    }

    /**
     * @return the fundGresbModel
     */
    public LineChartModel getFundGresbModel() {
        return fundGresbModel;
    }

    /**
     * @param fundGresbModel the fundGresbModel to set
     */
    public void setFundGresbModel(LineChartModel fundGresbModel) {
        this.fundGresbModel = fundGresbModel;
    }

    /**
     * @return the fundGreenPctModel
     */
    public LineChartModel getFundGreenPctModel() {
        return fundGreenPctModel;
    }

    /**
     * @param fundGreenPctModel the fundGreenPctModel to set
     */
    public void setFundGreenPctModel(LineChartModel fundGreenPctModel) {
        this.fundGreenPctModel = fundGreenPctModel;
    }

    /**
     * @return the regionGresbModel
     */
    public LineChartModel getRegionGresbModel() {
        return regionGresbModel;
    }

    /**
     * @param regionGresbModel the regionGresbModel to set
     */
    public void setRegionGresbModel(LineChartModel regionGresbModel) {
        this.regionGresbModel = regionGresbModel;
    }

    /**
     * @return the sectorGresbModel
     */
    public LineChartModel getSectorGresbModel() {
        return sectorGresbModel;
    }

    /**
     * @param sectorGresbModel the sectorGresbModel to set
     */
    public void setSectorGresbModel(LineChartModel sectorGresbModel) {
        this.sectorGresbModel = sectorGresbModel;
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

    /**
     * @return the testData
     */
    public List<Double> getTestData() {
        return testData;
    }

    /**
     * @param testData the testData to set
     */
    public void setTestData(List<Double> testData) {
        this.testData = testData;
    }

    /**
     * @return the fundIdentifier
     */
    public String getFundIdentifier() {
        return fundIdentifier;
    }

    /**
     * @param fundIdentifier the fundIdentifier to set
     */
    public void setFundIdentifier(String fundIdentifier) {
        this.fundIdentifier = fundIdentifier;
    }

}
