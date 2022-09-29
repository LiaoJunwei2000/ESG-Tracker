/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.File;
import javax.ejb.Local;

/**
 *
 * @author xindi
 */
@Local
public interface csvFileSessionLocal {
    public void uploadCsvFile(File csv);    
}
