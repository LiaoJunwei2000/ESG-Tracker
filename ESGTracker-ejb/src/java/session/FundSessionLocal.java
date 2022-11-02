/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

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
    
    public void createFundUser(FundUser u); 
    
    
    
    
}
