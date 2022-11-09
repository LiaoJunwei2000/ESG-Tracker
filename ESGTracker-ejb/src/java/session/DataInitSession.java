/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package session;

import entity.Fund;
import exception.NoResultException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author plant
 */
@Singleton
@LocalBean
@Startup
public class DataInitSession {

    @EJB
    private FundSessionLocal fundSessionLocal;

    @PostConstruct
    public void postConstruct() {
        try {
            fundSessionLocal.getFund(1L);
        } catch (NoResultException ex) {
            initializeData();
        }
    }

    private void initializeData() {
        //call addFundX addAssetX here
        addFund();
    }

    public void addFund() {
        Fund f = new Fund();
        f.setName("1");
        f.setFundIdentifier("1");
        f.setFyear(1);
        f.setFquarter(1);
        f.setGRESBRating(1);
        f.setInTFCD(true);
        f.setInUNRPI(true);

        fundSessionLocal.createFund(f);
    }

//        public void addAsset() {
//        Asset a = new Asset();
//        a.setFundId(fundId);
//        a.setAyear(fyear);
//        a.setAquarter(fquarter);
//        a.setName(assetName);
//        a.setAvalue(avalue);
//        a.setIsGreen(isGreen);
//        a.setCountry(country);
//        a.setFundName(fundName);
//        a.setSector(sector);
//        a.setRegion(region);
//
//        fundSessionLocal.createAsset(a);
//
//    }
}
