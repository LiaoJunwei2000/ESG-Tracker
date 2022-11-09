/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package session;

import entity.Asset;
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
        addFund1();
    }

    public void addFund() {
        Fund f = new Fund();
        f.setName("Invespa Real Estate Asia Fund");
        f.setFundIdentifier("AS1");
        f.setFyear(2022);
        f.setFquarter(1);
        f.setGRESBRating(72);
        f.setInTFCD(true);
        f.setInUNRPI(true);

        fundSessionLocal.createFund(f);
    }

    public void addFund1() {
        Fund f = new Fund();
        f.setName("Megan Stanley Prime Fund");
        f.setFundIdentifier("US1");
        f.setFyear(2022);
        f.setFquarter(1);
        f.setGRESBRating(76);
        f.setInTFCD(true);
        f.setInUNRPI(false);

        fundSessionLocal.createFund(f);
    }

    public void addAsset() throws NoResultException {
        Fund f = fundSessionLocal.getFund(1L);
        Asset a = new Asset();
        a.setFundId(f.getId());
        a.setAyear(f.getFyear());
        a.setAquarter(f.getFquarter());
        a.setName("K-Office");
        a.setAvalue(20000000);
        a.setIsGreen(true);
        a.setCountry("");
        a.setFundName(f.getName());
        a.setSector("Office");
        a.setRegion("Asia_Pacific");

        fundSessionLocal.createAsset(a);

    }
}
