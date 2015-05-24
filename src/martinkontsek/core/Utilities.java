/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package martinkontsek.core;

import java.io.File;

/**
 *
 * @author Martin Kontsek
 */
public class Utilities 
{
    public static boolean createDir(File paDir)
    {
        boolean result = false;
        
        if (!paDir.exists()) {           

            try{
                paDir.mkdir();
                result = true;
            } 
            catch(SecurityException se) 
            {
                //handle it
            }      
            
        } else {
            result = true;
        }
        
        return result;
    }
    
}
