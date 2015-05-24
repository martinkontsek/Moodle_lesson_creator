/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package martinkontsek.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Martin Kontsek
 */
public class HashGenerator 
{
    public static String generateHash(File paFile)
    {
        String computedHash = null;
        
        try {
            //throws NoSuchAlgorithmException
            MessageDigest md = MessageDigest.getInstance("SHA1");
            
            //throws FileNotFoundException
            FileInputStream fis = new FileInputStream(paFile);
            byte[] dataBytes = new byte[1024];

            int nread = 0; 

            //throws IOException
            while ((nread = fis.read(dataBytes)) != -1) {
              md.update(dataBytes, 0, nread);
            };

            byte[] mdbytes = md.digest();

            //convert the byte to hex format
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }       

            computedHash = sb.toString();
        
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        } catch (NoSuchAlgorithmException ex) {            
        }
        
        return computedHash;
    }
    
}
