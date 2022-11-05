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
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

        oldf.setValue(f.getValue());
        oldf.setQuarter(f.getQuarter());
        oldf.setPercentGreenByValue(f.getPercentGreenByValue());
        oldf.setName(f.getName());
        oldf.setInUNRPI(f.isInUNRPI());
        oldf.setInTFCD(f.isInTFCD());
        oldf.setGRESBRating(f.getGRESBRating());
        oldf.setAssets(f.getAssets());
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

        double fvalue = fund.getValue();
        double currentGreenValue = fund.getGreenValue();

        double avalue = a.getValue();
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
        fund.setValue(newValue);

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

        double oldAssetValue = oldA.getValue();
        double newAssetValue = a.getValue();
        boolean oldIsGreen = oldA.isIsGreen();
        boolean newIsGreen = a.isIsGreen();

        double fundValue = fund.getValue();
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

        fund.setValue(newTotalValue);
        fund.setGreenValue(fundGreen);
        fund.setPercentGreenByValue(percentGreen);

        oldA.setValue(newAssetValue);
        oldA.setCountry(a.getCountry());
        oldA.setIsGreen(a.isIsGreen());
        oldA.setName(a.getName());
        oldA.setRegion(a.getRegion());
        oldA.setSector(a.getSector());

    }

    @Override
    public void deleteAsset(Long aId) throws NoResultException {
        Asset a = getAsset(aId);
        Fund fund = getFund(a.getFundId());

        double fvalue = fund.getValue();
        double currentGreenValue = fund.getGreenValue();

        double avalue = a.getValue();
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
        fund.setValue(newValue);

        List<Asset> assets = fund.getAssets();
        assets.remove(a);
        em.remove(a);

    }

}
