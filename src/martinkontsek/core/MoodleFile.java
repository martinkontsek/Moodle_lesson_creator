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
    private boolean aPreviousBtn;
    private boolean aNextBtn;
    
    public MoodleFile(File paSourceFile) 
    {
        aSourceFile = paSourceFile;        
        
        aHash = HashGenerator.generateHash(aSourceFile);
        aResultFileName = this.getSourceFileName();
        aFileID = 0;
        aPreviousBtn = true;
        aNextBtn = true;
    }
       
    public void setResultFileName(String paResultFileName)
    {
        String extension = "";
        int i = this.getSourceFileName().lastIndexOf('.');
        if (i > 0) {
            extension = this.getSourceFileName().substring(i+1);
        }
        aResultFileName = paResultFileName+"."+extension;
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

    public boolean getPreviousBtn() 
    {
        return aPreviousBtn;
    }

    public void setPreviousBtn(boolean paPreviousBtn) 
    {
        this.aPreviousBtn = paPreviousBtn;
    }

    public boolean getNextBtn() 
    {
        return aNextBtn;
    }

    public void setNextBtn(boolean paNextBtn) 
    {
        this.aNextBtn = paNextBtn;
    }
            
    public void saveFileMoodle(File paDir)
    {
        Utilities.createDir(paDir);
        File outputDir = new File(paDir, aHash.substring(0, 2));
        Utilities.createDir(outputDir);
        
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
