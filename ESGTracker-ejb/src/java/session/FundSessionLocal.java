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
import javax.ejb.Local;

/**
 *
 * @author xindi
 */
@Local
public interface FundSessionLocal {
    
    public void Login(String username, String password) throws NoResultException;
    
    public FundUser getFundUser(Long uId) throws NoResultException;
    
    public void createFundUser(FundUser u); 
    
    public void updateFundUser(FundUser u) throws NoResultException;
    
    public void deleteFundUser(Long uId) throws NoResultException;
    
    public Fund getFund(Long fId) throws NoResultException;
    
    public void createFund(Fund f);
    
    public void updateFund(Fund f) throws NoResultException;
    
    public void deleteFund(Long fId) throws NoResultException;
    
    public Asset getAsset(Long aId) throws NoResultException; 
    
    public void createAsset(Asset a) throws NoResultException;
    
    public void updateAsset(Asset a) throws NoResultException;
    
    public void deleteAsset(Long aId) throws NoResultException;
    
}
