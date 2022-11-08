/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class LineChartManagedBean {

    private LineChartModel model;
    private LineChartModel portGresbModel;
    private LineChartModel portGreenPctModel;
    private LineChartModel fundGresbModel;
    private LineChartModel fundGreenPctModel;
    private LineChartModel regionGresbModel;
    private LineChartModel sectorGresbModel;

    public LineChartManagedBean() {
        createPortGresbModel();
        createPortGreenPctModel();
    }

    private void createPortGresbModel() {
        setPortGresbModel(new LineChartModel());
        ChartSeries scores = new ChartSeries();
        scores.setLabel("Portfolio");
        scores.set("2020 Q2", 120);
        scores.set("2020 Q3", 120);
        scores.set("2020 Q4", 120);
        scores.set("2021 Q1", 120);
        scores.set("2021 Q2", 120);
        scores.set("2021 Q3", 120);
        scores.set("2021 Q4", 120);
        scores.set("2022 Q1", 120);
        scores.set("2022 Q2", 120);
        scores.set("2022 Q3", 120);
        getPortGresbModel().addSeries(scores);

        getPortGresbModel().setTitle("Portfolio Quarterly GRESB");
        getPortGresbModel().setLegendPosition("e");
        getPortGresbModel().setShowPointLabels(true);
        getPortGresbModel().getAxes().put(AxisType.X, new CategoryAxis("Quarter"));
        Axis yAxis = getPortGresbModel().getAxis(AxisType.Y);
        yAxis.setLabel("GRESB Score");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private void createPortGreenPctModel() {
        setPortGreenPctModel(new LineChartModel());
        ChartSeries scores = new ChartSeries();
        scores.setLabel("Portfolio");
        scores.set("2020 Q2", 120);
        scores.set("2020 Q3", 120);
        scores.set("2020 Q4", 120);
        scores.set("2021 Q1", 120);
        scores.set("2021 Q2", 120);
        scores.set("2021 Q3", 120);
        scores.set("2021 Q4", 120);
        scores.set("2022 Q1", 120);
        scores.set("2022 Q2", 120);
        scores.set("2022 Q3", 120);
        getPortGreenPctModel().addSeries(scores);

        getPortGreenPctModel().setTitle("Portfolio Green Building %");
        getPortGreenPctModel().setLegendPosition("e");
        getPortGreenPctModel().setShowPointLabels(true);
        getPortGreenPctModel().getAxes().put(AxisType.X, new CategoryAxis("Quarter"));
        Axis yAxis = getPortGreenPctModel().getAxis(AxisType.Y);
        yAxis.setLabel("Green Building %");
        yAxis.setMin(0);
        yAxis.setMax(200);
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

}
