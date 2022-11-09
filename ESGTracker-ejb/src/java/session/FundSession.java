/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Asset;
import entity.Fund;
import entity.FundUser;
import exception.NoResultException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author xindi
 */
@Stateless
public class FundSession implements FundSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void Login(String username, String password) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FundUser getFundUser(Long uId) throws NoResultException {
        FundUser user = em.find(FundUser.class, uId);

        if (user != null) {
            return user;
        } else {
            throw new NoResultException("User not found");
        }
    }

    @Override
    public Fund getFund(Long fId) throws NoResultException {
        Fund fund = em.find(Fund.class, fId);

        if (fund != null) {
            return fund;
        } else {
            throw new NoResultException("Fund not found");
        }
    }

    @Override
    public Asset getAsset(Long aId) throws NoResultException {
        Asset asset = em.find(Asset.class, aId);

        if (asset != null) {
            return asset;
        } else {
            throw new NoResultException("Asset not found");
        }
    }

    @Override
    public void createFundUser(FundUser u) {
        em.persist(u);
    }

    @Override
    public void updateFundUser(FundUser u) throws NoResultException {
        FundUser oldu = getFundUser(u.getId());

        oldu.setName(u.getName());
        oldu.setPassword(u.getPassword());
    }

    @Override
    public void deleteFundUser(Long uId) throws NoResultException {
        FundUser user = getFundUser(uId);

        em.remove(user);
    }

    @Override
    public void createFund(Fund f) {
        em.persist(f);
        
    }

    @Override
    public void updateFund(Fund f) throws NoResultException {
        Fund oldf = getFund(f.getId());
        List<Asset> assets = oldf.getAssets();
        
        oldf.setFyear(f.getFyear());
        oldf.setFquarter(f.getFquarter());
        oldf.setFundIdentifier(f.getFundIdentifier());
        oldf.setName(f.getName());
        oldf.setInUNRPI(f.isInUNRPI());
        oldf.setInTFCD(f.isInTFCD());
        oldf.setGRESBRating(f.getGRESBRating());
        
        for(Asset a : assets) {
            a.setFundId(f.getId());
            a.setAyear(f.getFyear());
            a.setAquarter(f.getFquarter());
            a.setFundName(f.getName());
        }
        oldf.setAssets(assets);
    }

    @Override
    public void deleteFund(Long fId) throws NoResultException {
        Fund fund = getFund(fId);

        List<Asset> assets = fund.getAssets();
        fund.setAssets(null);

        for (Asset a : assets) {
            //remove the asset entry when deleting the fund entry, because each asset entry is unqiue to a fund
            em.remove(a);
        }

        em.remove(fund);
    }

    
    @Override
    public void createAsset(Asset a) throws NoResultException {
        Long fId = a.getFundId();
        Fund fund = getFund(fId);

        double fvalue = fund.getFvalue();
        double currentGreenValue = fund.getGreenValue();

        double avalue = a.getAvalue();
        boolean assetIsGreen = a.isIsGreen();

        double newValue = fvalue + avalue;
        double newPercent;

        if (assetIsGreen) {
            double newGreen = currentGreenValue + avalue;
            newPercent = newGreen / newValue * 100;
            fund.setGreenValue(newGreen);
        } else {
            newPercent = currentGreenValue / newValue * 100;
        }

        fund.setPercentGreenByValue(newPercent);
        fund.setFvalue(newValue);

        //aggregateAssetToFund(a);
        List<Asset> assets = fund.getAssets();
        assets.add(a);

        em.persist(a);
    }

    //assumption: user cannot change the fund that the asset belongs to as well as the quarter when updating asset
    // i.e. the primary keys {fundId, quarter, assetId} cannot be changed
    @Override
    public void updateAsset(Asset a) throws NoResultException {
        Asset oldA = getAsset(a.getId());
        Long fId = a.getFundId();
        Fund fund = getFund(fId);

        double oldAssetValue = oldA.getAvalue();
        double newAssetValue = a.getAvalue();
        boolean oldIsGreen = oldA.isIsGreen();
        boolean newIsGreen = a.isIsGreen();

        double fundValue = fund.getFvalue();
        double fundGreen = fund.getGreenValue();

        double newTotalValue = fundValue - oldAssetValue + newAssetValue;
        double percentGreen = fund.getPercentGreenByValue();

        //old yes new no; old no new yes; old yes new yes; old no new no;
        /*
        if(oldIsGreen == TRUE && newIsGreen == FALSE) {
            fundGreen = fundGreen - oldAssetValue;
            percentGreen = fundGreen / newTotalValue * 100;
        } else if(oldIsGreen == FALSE && newIsGreen == TRUE) {
            fundGreen = fundGreen + newAssetValue;
            percentGreen = fundGreen / newTotalValue * 100;
        } else if(oldIsGreen == TRUE && newIsGreen == TRUE) {
            fundGreen = fundGreen - oldAssetValue + newAssetValue;
            percentGreen = fundGreen / newTotalValue * 100;
        } else if(oldIsGreen == FALSE && newIsGreen == FALSE) {
            percentGreen = fundGreen / newTotalValue * 100;
        }
         */
        if (oldIsGreen) {
            fundGreen -= oldAssetValue;
        }

        if (newIsGreen) {
            fundGreen += newAssetValue;
        }

        percentGreen = fundGreen / newTotalValue * 100;

        fund.setFvalue(newTotalValue);
        fund.setGreenValue(fundGreen);
        fund.setPercentGreenByValue(percentGreen);

        oldA.setAvalue(newAssetValue);
        oldA.setCountry(a.getCountry());
        oldA.setIsGreen(a.isIsGreen());
        oldA.setName(a.getName());
        oldA.setRegion(a.getRegion());
        oldA.setSector(a.getSector());
        oldA.setFundName(fund.getName());

    }

    @Override
    public void deleteAsset(Long aId) throws NoResultException {
        Asset a = getAsset(aId);
        Fund fund = getFund(a.getFundId());

        double fvalue = fund.getFvalue();
        double currentGreenValue = fund.getGreenValue();

        double avalue = a.getAvalue();
        boolean assetIsGreen = a.isIsGreen();

        double newValue = fvalue - avalue;
        double newPercent;

        if (assetIsGreen) {
            double newGreen = currentGreenValue - avalue;
            newPercent = newGreen / newValue * 100;
            fund.setGreenValue(newGreen);
        } else {
            newPercent = currentGreenValue / newValue * 100;
        }

        fund.setPercentGreenByValue(newPercent);
        fund.setFvalue(newValue);

        List<Asset> assets = fund.getAssets();
        assets.remove(a);
        em.remove(a);

    }

    @Override
    public List<Fund> searchFunds(String name) {
        Query q;
        if (name != null) {
            q = em.createQuery("SELECT f FROM Fund f WHERE LOWER(f.name) LIKE :name");
            q.setParameter("name", "%" + name.toLowerCase() + "%");
        } else {
            q = em.createQuery("SELECT f FROM Fund f");
        }
        
        return q.getResultList();
    }

    @Override
    public List<Asset> searchAssets(String name) {
        Query q;
        if (name != null) {
            q = em.createQuery("SELECT a FROM Asset a WHERE LOWER(a.name) LIKE :name");
            q.setParameter("name", "%" + name.toLowerCase() + "%");
        } else {
            q = em.createQuery("SELECT a FROM Asset a");
        }
        
        return q.getResultList();
    }
    
    @Override
    public List<Double> getPortGresbList() {
        
        List<Integer> yearList = getYearList();
        List<Integer> quarterList = getQuarterList();
        
        List<Double> resultList = new ArrayList<>();
        
        for (int i = 0; i < 9; i++) {
            double quarterValue = 0;
            double weightedScore = 0;
            
            List<Fund> fundList = getAllFunds(yearList.get(i), quarterList.get(i));
            
            for (Fund f : fundList) {
                double tempRating = f.getGRESBRating();
                double tempValue = f.getFvalue();
                quarterValue += tempValue;
                weightedScore += tempRating * tempValue;
            }
            
            resultList.add(weightedScore / quarterValue);
        }
        return resultList;
    }
    
    @Override
    public List<Double> getPortGreenPctList() {
        
        List<Integer> yearList = getYearList();
        List<Integer> quarterList = getQuarterList();
        
        List<Double> resultList = new ArrayList<>();
        
        for (int i = 0; i < 9; i++) {
            double quarterValue = 0;
            double greenValue = 0;
            
            List<Fund> fundList = getAllFunds(yearList.get(i), quarterList.get(i));
            
            for (Fund f : fundList) {
                double tempGreenValue = f.getGreenValue();
                double tempValue = f.getFvalue();
                quarterValue += tempValue;
                greenValue += tempGreenValue;
            }
            
            resultList.add(100 * (greenValue / quarterValue));
        }
        return resultList;
    }
    
    @Override
    public List<Double> getFundGresbList(String fundId){
        
        List<Fund> quarterList = getFundFromFundIdentifier(fundId);
        List<Double> resultList = new ArrayList<>();
        
        for (int i = 0; i < 9; i++) {
            resultList.add((double)quarterList.get(i).getGRESBRating());
        }
        
        return resultList;
    }
    
    @Override
    public List<Double> getFundGreenPct(String fundId){
        
        List<Fund> quarterList = getFundFromFundIdentifier(fundId);
        List<Double> resultList = new ArrayList<>();
        
        for (int i = 0; i < 9; i++) {
            double tempValue = (double)quarterList.get(i).getFvalue();
            double tempGreenValue = (double)quarterList.get(i).getGreenValue();
            
            resultList.add(100 * (tempGreenValue / tempValue));
        }
        
        return resultList;
    }
    
    @Override
    public List<Double> getRegionGreenPctList(){
        
        
        
        return getTestList();
    }
    
    @Override
    public List<Double> getSectorGreenPctList(){

        
        
        return getTestList();
    }
    
    
    
    /* helper methods below. 
    Currently getting a set of quarters based on 2022 Q3 
    
    todo input selected quarter and get 10 qtr data back.
    
    
    */
    
    //gets all quarters of fund ordered by latest quarter first
    private List<Fund> getFundFromFundIdentifier(String fundIdentifier) {
        Query q;
        
        q = em.createQuery("SELECT f FROM Fund f WHERE f.fundIdentifier = :inFundIdentifier ORDER BY f.fYear, f.fQuarter DESC");
        q.setParameter("inFundId", fundIdentifier);
        return (List<Fund>)q.getResultList();
    }
    
    private List<Fund> getAllFunds(int fYear, int fQuarter){
        
        Query q;
        
        q = em.createQuery("SELECT f FROM Fund f WHERE f.fyear = :inFYear AND f.fquarter = :inFQuarter ");
        q.setParameter("inFYear", fYear);
        q.setParameter("inFQuarter", fQuarter);
        
        return q.getResultList();
    }
    
    private List<Integer> getYearList() {
        ArrayList<Integer> yearList = new ArrayList<>();
        yearList.add(2022);
        yearList.add(2022);
        yearList.add(2022);
        yearList.add(2021);
        yearList.add(2021);
        yearList.add(2021);
        yearList.add(2021);
        yearList.add(2020);
        yearList.add(2020);
        yearList.add(2020);
        
        return yearList;
    }
    
    private List<Integer> getQuarterList() {
        ArrayList<Integer> quarterList = new ArrayList<>();
        quarterList.add(3);
        quarterList.add(2);
        quarterList.add(1);
        quarterList.add(4);
        quarterList.add(3);
        quarterList.add(2);
        quarterList.add(1);
        quarterList.add(4);
        quarterList.add(3);
        quarterList.add(2);
        
        return quarterList;
    }
    
    //returns a default list of size 10 for frontend testing
    private List<Double> getTestList() {
        ArrayList<Double> testList = new ArrayList<>();
        testList.add((double) 55);
        testList.add((double) 58);
        testList.add((double) 63);
        testList.add((double) 65);
        testList.add((double) 72);
        testList.add((double) 77);
        testList.add((double) 81);
        testList.add((double) 84);
        testList.add((double) 83);
        testList.add((double) 86);
        return testList;
    }
}
