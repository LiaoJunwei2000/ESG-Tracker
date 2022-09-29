/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author xindi
 */
@Stateless
public class csvFileSession implements csvFileSessionLocal {

    @Override
    public void uploadCsvFile(File csv) {
        try {
            Scanner sc = new Scanner(csv);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(csvFileSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
