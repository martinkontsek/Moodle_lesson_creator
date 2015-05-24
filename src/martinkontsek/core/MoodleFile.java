/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package martinkontsek.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Martin Kontsek
 */
public class MoodleFile 
{
    private File aSourceFile;    
    private String aHash;  
    private String aResultFileName;
    private int aFileID;
    
    public MoodleFile(File paSourceFile) 
    {
        aSourceFile = paSourceFile;        
        
        aHash = HashGenerator.generateHash(aSourceFile);
        aResultFileName = this.getSourceFileName();
        aFileID = 0;
    }
    
    public boolean createDir(File paDir)
    {
        boolean result = false;
        
        if (!paDir.exists()) {           

            try{
                paDir.mkdir();
                result = true;
            } 
            catch(SecurityException se){
                //handle it
            }      
            
        } else {
            result = true;
        }
        
        return result;
    }
    
    public void setResultFileName(String paResultFileName)
    {
        aResultFileName = paResultFileName;
    }
    
    public String getResultFileName()
    {
        return aResultFileName;
    }
    
    public String getSourceFileName()
    {
        return aSourceFile.getName();
    }
    
    public String getHash()
    {
        return aHash;
    }
    
    public long getSourceFileSize()
    {
        return aSourceFile.length();
    }
    
    public void setFileID(int paFileID)
    {
        if(paFileID < 1)
            return;
        aFileID = paFileID;
    }
    
    public int getFileID()
    {
        return aFileID;
    }
    
    public void saveFileMoodle(File paDir)
    {
        this.createDir(paDir);
        File outputDir = new File(paDir, aHash.substring(0, 2));
        this.createDir(outputDir);
        
        File outputFile = new File(outputDir, aHash);
        
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(aSourceFile);
            os = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            
            is.close();
            os.close();
        } catch (IOException ex) {
        }
    }
}
